package com.lee.sharding.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.sharding.entity.User;
import com.lee.sharding.param.UserParam;
import com.lee.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lee
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(UserParam param) {
        QueryWrapper queue = new QueryWrapper<>();
        queue.gt("create_date", param.getCreateDate());
        // queue.ge("create_date", param.getCreateDate());
        // queue.eq("create_date", param.getCreateDate());
        // queue.between("create_date", param.getCreateDate(), param.getCreateDate());
        return userService.list(queue);
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping
    public User get(Integer id) {
        return userService.getById(id);
    }
}
