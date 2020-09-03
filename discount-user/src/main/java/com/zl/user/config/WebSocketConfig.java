//package com.zl.user.config;
//
//import com.zl.common.interceptor.WebSocketHandshakeInterceptor;
//import com.zl.user.websocket.MyWebSocketHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.*;
//
///**
// * Created by zhangliang on 2018/12/24.
// */
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Autowired
//    private WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;
//
//    @Autowired
//    private MyWebSocketHandler myWebSocketHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//
//        //handler是webSocket的核心，配置入口
//        registry.addHandler(myWebSocketHandler, "/websocket").setAllowedOrigins("*").addInterceptors(webSocketHandshakeInterceptor);
//
//    }
//
//}