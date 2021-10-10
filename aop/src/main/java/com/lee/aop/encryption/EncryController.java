package com.lee.aop.encryption;

import com.lee.aop.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试加密
 */
@RestController
@RequestMapping("/encry")
public class EncryController {

    @Encry("tel")
    @RequestMapping("/test")
    public R test(){
        Map map = new HashMap();
        map.put("name", "test");
        map.put("tel", "123456");
        return R.Source(map);
    }

    @Encry("phone")
    @GetMapping("/test/{data}")
    public R test(@PathVariable("data") String data){
        Map map = new HashMap();
        map.put("name", "test2");
        map.put("phone", data);
        return R.Source(map);
    }

    @Encry("phone")
    @RequestMapping("/test2")
    public R test2(@RequestParam("phone") String phone){
        Map map = new HashMap();
        map.put("name", "test2");
        map.put("phone", phone);
        return R.Source(map);
    }

    @RequestMapping("/test1")
    public R test1(){
        Map map = new HashMap();
        map.put("name", "test1");
        map.put("tel", "123456789");
        return R.Source(map);
    }

}
