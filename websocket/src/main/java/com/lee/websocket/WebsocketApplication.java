package com.lee.websocket;

import com.lee.websocket.netty.NettyWebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author lee
 */
@SpringBootApplication
public class WebsocketApplication {

    @Resource
    private NettyWebSocketServer nettyWebSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }
}