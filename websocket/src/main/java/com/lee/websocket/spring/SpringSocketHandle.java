package com.lee.websocket.spring;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 */
@Component
public class SpringSocketHandle implements WebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        System.out.println("SpringSocketHandle, 收到新的连接: " + session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // String msg = "SpringSocketHandle, 连接：" + session.getId() + "，已收到消息。" + message.getPayload();
        String[] split = message.getPayload().toString().split("&&");
        String msg = session.getId() + " 发来消息：" + split[1];
        System.out.println(msg);
        if (split[1].contains("len")) {
            session.sendMessage(new TextMessage("连接数：" + sessions.size()));
        } else if (split[0].isEmpty()) {
            session.sendMessage(new TextMessage(session.getId()));
        } else {
            sessions.get(split[0]).sendMessage(new TextMessage(msg));
        }
        // session.sendMessage(new TextMessage(msg));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessions.remove(session.getId());
        System.out.println(session.getId() + "WS 连接发生错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session.getId());
        System.out.println(session.getId() + "WS 关闭连接" + sessions.size());
    }

    // 支持分片消息
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}