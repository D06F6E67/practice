package com.lee.aop.lock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lee
 */
@RestController
@RequestMapping("/lock")
public class LockController {

     @Resource
     private A a;
     @Resource
     private B b;

    @GetMapping("/a")
    public void a() throws InterruptedException {
        a.executeA();
    }

    @GetMapping("/b")
    public List<Integer> b() throws InterruptedException {
        return b.executeB();
    }
}
