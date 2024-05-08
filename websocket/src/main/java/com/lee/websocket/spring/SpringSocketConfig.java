package com.lee.websocket.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @author lee
 */
@Configuration
@EnableWebSocket
public class SpringSocketConfig implements WebSocketConfigurer {
    @Resource
    private SpringSocketHandle springSocketHandle;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(springSocketHandle, "/spring-ws").setAllowedOrigins("*");
    }
}