package com.lee.netty.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端
 *
 * @author Lee
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private Map<String, MyClient> myClientMap = new HashMap<>();


    @GetMapping("/start/{name}")
    private void start(@PathVariable("name") String name) {
        MyClient myClient = new MyClient(8888);
        myClientMap.put(name, myClient);
    }

    @GetMapping("/send/{name}/{msg}")
    private void send(@PathVariable("name") String name,
                      @PathVariable("msg") String msg) {
        MyClient myClient = myClientMap.get(name);
        myClient.send(msg);
    }
}
