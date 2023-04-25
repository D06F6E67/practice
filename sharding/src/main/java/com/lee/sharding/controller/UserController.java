package com.lee.sharding.controller;

import com.lee.sharding.ExamineDate;
import com.lee.sharding.entity.dto.UserDTO;
import com.lee.sharding.entity.param.UserParam;
import com.lee.sharding.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lee
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ExamineDate
    @GetMapping("/list")
    public List<UserDTO> list(UserParam param) {
        return userService.list(param);
    }

    @ExamineDate
    @PutMapping
    public void save(@RequestBody UserDTO user) {
        userService.save(user);
    }

    @ExamineDate
    @GetMapping
    public UserDTO get(UserParam param) {
        return userService.getOne(param);
    }

    @ExamineDate
    @PostMapping
    public void update(@RequestBody UserDTO user) {
        userService.update(user);
    }
}
