package com.lee.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.sharding.entity.User;
import com.lee.sharding.mapper.UserMapper;
import com.lee.sharding.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Lee
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
