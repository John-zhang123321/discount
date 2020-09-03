package com.zl.user.websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangliang on 2018/12/24.
 *
 */

@Component
@Slf4j
public class MyWebSocketHandler extends AbstractWebSocketHandler {
    private static final Map<Long, WebSocketSession> users = new ConcurrentHashMap();

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        users.put(getClientId(session), session);
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.error("连接出错【{}】",getClientId(session));
        users.remove(getClientId(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(getClientId(session));
    }


    public void push2Customer(long userId,int prop){
        log.info("userid {} ,prop {}",userId,prop);
        WebSocketSession webSocketSession = users.get(userId);
        if (webSocketSession == null || !webSocketSession.isOpen()){
            return;
        }
        log.info("prop {}",prop);
        try {
            webSocketSession.sendMessage(new TextMessage(prop+""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long getClientId(WebSocketSession session) {
        Long id = (Long) session.getAttributes().get("id");
        log.info("id {}",id);
        return id;
    }
}
