package com.lee.sharrding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.sharrding.entity.User;
import com.lee.sharrding.mapper.UserMapper;
import com.lee.sharrding.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Lee
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
