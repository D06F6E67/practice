package com.lee.netty.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lee
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @Resource
    private MyServer myServer;

    @GetMapping
    public void ids() {
        myServer.myServerHandler.ids();
    }

    @GetMapping("/{id}/{msg}")
    public void send(@PathVariable("id") String id, @PathVariable("msg") String msg) {
        myServer.myServerHandler.send(id, msg);
    }
}
