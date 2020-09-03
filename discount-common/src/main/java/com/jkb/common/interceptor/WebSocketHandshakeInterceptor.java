//package com.zl.common.interceptor;
//
//import cn.hutool.json.JSONUtil;
//import com.zl.common.entity.TokenObject;
//import com.zl.common.utils.JwtTokenUtil;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.Map;
//
///**
// * websocket拦截器
// * Created by zhangliang on 2020/1/22
// */
//@Component
//@Slf4j
//public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        String token = getToken(request);
//        if (StringUtils.isBlank(token) || !JwtTokenUtil.isSigned(token)) {
//            return verificationFailed(response);
//        }
//
//        Claims claims = JwtTokenUtil.getMyClaimByToken(token);
//        if(claims == null){
//            return verificationFailed(response);
//        }
//
//        attributes.put("id",JSONUtil.toBean(claims.getSubject(), TokenObject.class).getId());
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
//                               Exception ex) {
//        log.info("Call afterHandshake");
//    }
//
//    /**
//     * 校验不通过
//     */
//    public boolean verificationFailed(ServerHttpResponse response) {
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        return false;
//    }
//    /**
//     * 获取请求Token
//     */
//    private String getToken(ServerHttpRequest request) {
//        return request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//    }
//}
