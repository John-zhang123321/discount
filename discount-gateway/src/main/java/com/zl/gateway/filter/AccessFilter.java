package com.zl.gateway.filter;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.zl.gateway.cache.RedisCacheContainer;
import com.zl.gateway.config.ApiErrorEnum;
import com.zl.gateway.config.SignatureProperties;
import com.zl.gateway.entity.*;
import com.zl.gateway.mapper.BlacklistDetailMapper;
import com.zl.gateway.mapper.BlacklistMapper;
import com.zl.gateway.mapper.ExcludePathMapper;
import com.zl.gateway.mapper.VisitLogMapper;
import com.zl.gateway.util.IdUtil;
import com.zl.gateway.util.JwtTokenUtil;
import com.zl.gateway.util.SignUtils;
import io.jsonwebtoken.Claims;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import static com.zl.gateway.util.JwtTokenUtil.parseJWT;

/**
 * Created by zhangliang on 2018/12/15.
 */
@Component
@Order(0)
public class AccessFilter implements GlobalFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);
    private static final String IP_UNKNOWN = "unknown";
    private static final String IP_LOCAL = "127.0.0.1";
    private static final int IP_LEN = 15;
    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String REDIS_USER_TOKEN = "REDIS_USER_TOKEN";

    private static final ExecutorService LOG_SERVICE = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() + 1,
            500, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10000), new ThreadFactory() {
        private AtomicLong count = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("VISIT_LOG_THREAD_" + count.incrementAndGet());
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private ExcludePathMapper excludePathMapper;

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Autowired
    private BlacklistDetailMapper blacklistDetailMapper;

    @Autowired
    private Audience audience;

    @Autowired
    private RedisCacheContainer redisCacheContainer;

    @Autowired
    private SignatureProperties properties;

    private List<ExcludePath> excludePaths;

    private List<Blacklist> blacklists;

    final Map<String, String> appKeyMap = Maps.newHashMap();

    @PostConstruct
    public void init() {
        this.excludePaths = excludePathMapper.getBySystem("0");
        this.blacklists = blacklistMapper.getBySystem("0");

        if (properties != null && properties.getAppList() != null && properties.getAppList().size() > 0) {

            properties.getAppList().stream()
                    .map(s -> {
                        List<String> list = Splitter.on(";")
                                .trimResults()
                                .splitToList(s);

                        SignatureProperties.Sig sig = new SignatureProperties.Sig();
                        sig.setAppId(list.get(0));
                        sig.setAppKey(list.get(1));
                        sig.setEnabled(Boolean.parseBoolean(list.get(2)));
                        return sig;
                    })
                    .filter(SignatureProperties.Sig::isEnabled)
                    .forEach(sig -> appKeyMap.put(sig.getAppId(), sig.getAppKey()));
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        String ua = request.getHeaders().getFirst(HttpHeaders.USER_AGENT);
        String path = request.getURI().getPath();

        String ipAddress = getClientIpAddress(request);
        //ip黑名单
        Pair<Boolean, Long> ipPair = phoneOrIpBlackList(ipAddress);
        if(ipPair.getKey()){
            insertBlackDetail(ipPair.getValue(),path,ua);
            LOGGER.info("拦截请求ip黑名单地址：{}",ipAddress);
            return rejectRequest(response, ApiErrorEnum.OVER_MANY);
        }

        String target = path.replace("/api", "");

        if (excludePaths.stream().anyMatch(excludePath -> Pattern.matches(excludePath.getPath(), target))) {
            return gatewayFilterChain.filter(serverWebExchange);
        }

        String timestamp = request.getQueryParams().getFirst("_timestamp");
        if (StringUtils.isBlank(timestamp)) {
            return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_TIMESTAMP_NULL_OR_EMPTY);
        } else {
            boolean isDigits = NumberUtils.isDigits(timestamp);
            if (!isDigits) {
                return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_TIMESTAMP_INVALID);
            }

            try {
                long stamp = Long.valueOf(timestamp);
                LocalDateTime requestTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(stamp), ZoneId.systemDefault());
                LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

                Duration duration = Duration.between(requestTime, now);
                long seconds = Math.abs(duration.getSeconds());

                if (seconds > 60 * 10) {
                    return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_TIMESTAMP_EXPIRED);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex.getMessage(), ex);
                return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_TIMESTAMP_INVALID);
            }
        }

        String signature = request.getQueryParams().getFirst("_sign");
        if (StringUtils.isBlank(signature)) {
            return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_SIGN_NULL_OR_EMPTY);
        }

        LOGGER.info("_sign: " + signature);

        String appKey;
        String appId = request.getQueryParams().getFirst("_appid");
        if (StringUtils.isBlank(appId)) {
            MultiValueMap<String, String> queryParams = request.getQueryParams();

            queryParams.toSingleValueMap().forEach((key, value) -> {
                LOGGER.info("key：{}, parameterName：{}", key, value);
            });

            return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_APPID_NULL_OR_EMPTY);
        } else {
            if (appKeyMap.containsKey(appId)) {
                appKey = appKeyMap.get(appId);
            } else {
                return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_APPID_INVALID);
            }
        }


        String method = request.getMethodValue();
        boolean valid;
        switch (method) {
            case "POST":
            case "PUT":
                String contentType = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
                boolean isFormPost = (contentType != null && contentType.contains(FORM_CONTENT_TYPE) && HttpMethod.POST.matches(method));

                if (isFormPost) {
                    valid = SignUtils.checkSign(appKey, request, null);
                } else {
                    String body = resolveBodyFromRequest(serverWebExchange.getRequest());
                    valid = SignUtils.checkSign(appKey, request, body);
                }
                break;
            default:
                valid = SignUtils.checkSign(appKey, request, null);
                break;
        }

        if (!valid) {
            return rejectRequest(response, ApiErrorEnum.BAD_REQUEST_SIGN_INVALID);
        }

        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return rejectRequest(response, ApiErrorEnum.TOKEN_INVALID);
        }

        try {
            Claims claims = parseJWT(token, audience.getBase64Secret());
            String phone = JwtTokenUtil.getUserPhone(token, audience.getBase64Secret());
            Pair<Boolean, Long> phonePair = phoneOrIpBlackList(phone);
            if(phonePair.getKey()){
                LOGGER.info("拦截请求手机黑名单地址：{}", phone);
                insertBlackDetail(phonePair.getValue(),"",ua);
                return rejectRequest(response, ApiErrorEnum.OVER_MANY);
            }
            insertVisitLog(ua,ipAddress, phone,path);

            String key = String.format("%s:%s", REDIS_USER_TOKEN, token);
            if (redisCacheContainer.get(key) == null) {
                return rejectRequest(response, ApiErrorEnum.UNAUTHORIZED);
            }
            redisCacheContainer.setKeyExpire(key,audience.getExpiresSecond());
            request.getHeaders().remove(HttpHeaders.AUTHORIZATION);
            ServerHttpRequest mutableReq = request.mutate().header(HttpHeaders.AUTHORIZATION, JSONUtil.toJsonStr(claims)).build();
//
            ServerWebExchange mutableExchange = serverWebExchange.mutate().request(mutableReq).build();

            return gatewayFilterChain.filter(mutableExchange);
        }  catch (Exception e) {
            LOGGER.error("parseClaimsJws error:{}",e.getMessage());
            return rejectRequest(response, ApiErrorEnum.TOKEN_INVALID);
        }
    }




    public Mono<Void> rejectRequest(ServerHttpResponse resp, ApiErrorEnum apiErrorEnum) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON.toString());
        Map res = new HashMap();
        res.put("code", apiErrorEnum.getCode());
        res.put("msg", apiErrorEnum.getErrMsg());
        res.put("data", null);
        DataBuffer bodyDataBuffer = resp.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes());

        return resp.writeWith(Mono.just(bodyDataBuffer));

    }

    /**
     * 获取请求Token
     */
    private String getToken(ServerHttpRequest request) {
        return request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    /**
     * 读取body内容
     *
     * @param serverHttpRequest
     * @return
     */
    public String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        StringBuilder sb = new StringBuilder();
        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            sb.append(bodyString);
        });
        return sb.toString();
    }

    public static String getClientIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ipAddress = headers.getFirst("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }

        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = Optional.ofNullable(request.getRemoteAddress())
                    .map(address -> address.getAddress().getHostAddress())
                    .orElse("");
            if (IP_LOCAL.equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    LOGGER.error("getClientIpAddress error. Msg: {}", e.getMessage());
                }
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > IP_LEN) {
            int index = ipAddress.indexOf(",");
            if (index > 0) {
                ipAddress = ipAddress.substring(0, index);
            }
        }
        return ipAddress;
    }


    public void insertBlackDetail(Long blacklistId,String path,String ua){
        LOG_SERVICE.execute(() -> {
            String format = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);
            String[] sp = StringUtils.split(format, " ");
            BlacklistDetail blacklistDetail = new BlacklistDetail();
            blacklistDetail.setId(IdUtil.nextId());
            blacklistDetail.setBlacklistId(blacklistId);
            blacklistDetail.setVisitDate(sp[0]);
            blacklistDetail.setVisitTime(sp[1]);
            blacklistDetail.setVisitPath(path);
            blacklistDetail.setVisitSource(ua);
            blacklistMapper.updateById(blacklistId);
            blacklistDetailMapper.insertSelective(blacklistDetail);
        });
    }

    private void insertVisitLog(String uaStr,String ipAddress,String phone,String path) {
        UserAgent ua = UserAgentUtil.parse(uaStr);
        LOG_SERVICE.execute(() -> {
            VisitLog visitLog = new VisitLog();
            visitLog.setId(IdUtil.nextId());
            visitLog.setIp(ipAddress);
            visitLog.setPhone(phone);
            visitLog.setSystem("0");
            visitLog.setVisitTime(new Date());
            visitLog.setVisitPath(path);
            visitLog.setBrowser(ua.getBrowser().toString());
            visitLog.setEngine(ua.getEngine().toString());
            visitLog.setEngineVersion(ua.getEngineVersion());
            visitLog.setOs(ua.getOs().toString());
            visitLog.setPlatform(ua.getPlatform().toString());
            visitLog.setVersion(ua.getVersion());
            visitLog.setMobile(ua.isMobile());
            String tableName = "visit_log_"+DateUtil.format(new Date(), "yyyyMM");
            visitLogMapper.insertLog(tableName,visitLog);
        });
    }

    public Pair<Boolean,Long> phoneOrIpBlackList(String str){
        if (null == str || str.isEmpty()){
            return Pair.of(false,null);
        }
        Optional<Blacklist> first = blacklists.stream().filter(w -> Objects.equals(w.getStr(), str)).findFirst();
        if(first.isPresent()){
            return Pair.of(true,first.get().getId());
        }

        return Pair.of(false,null);
    }
}
