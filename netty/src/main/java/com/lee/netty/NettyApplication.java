package com.lee.netty;

import com.lee.netty.server.MyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * netty
 *
 * @author Lee
 */
@SpringBootApplication
public class NettyApplication {

    @Resource
    private MyServer myServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
    }
}