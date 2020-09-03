package com.jkb.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.jkb.common.base.Audience;
import com.jkb.common.entity.TokenObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Created by zhangliang on 2018/12/15.
 */
@Slf4j
public class RequestResponseUtil {
    public static long getUserId() {
        Object value = getTokenFiledValue("userId");
        if (value == null) {
            return -1L;
        }
        return (Long) getTokenFiledValue("userId");
    }


    public static Object getTokenFiledValue(String fieldName) {
        Assert.notBlank(fieldName);
        if(getTokenObject() == null){
            return -1L;
        }
        return BeanUtil.getFieldValue(getTokenObject(), fieldName);
    }

    private static TokenObject getTokenObject() {
        String token =  getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        TokenObject tokenObject = JSON.parseObject(token, TokenObject.class);
        log.info("tokenObject-------------->{}", JSONUtil.toJsonStr(tokenObject));
        return tokenObject;
    }

    private static  ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }


    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static void setToken(String token) {
       getServletRequestAttributes().getResponse().setHeader(HttpHeaders.AUTHORIZATION,token);
    }
    public Long getWxToken() {
        return  (Long) getRequest().getAttribute("user");
    }


    public static String getClientIpAddress() {
        String ipAddress;
        HttpServletRequest request = getRequest();
        ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if ("127.0.0.1".equals(ipAddress)) {
                InetAddress inetAddress;
                try {
                    inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException ex) {
                    log.error("getClientIpAddress error. Msg: {}", ex.getMessage(), ex);
                }
            }
        }

        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }
}
