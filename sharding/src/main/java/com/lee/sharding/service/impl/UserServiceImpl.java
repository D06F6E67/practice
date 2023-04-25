package com.lee.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.sharding.entity.User;
import com.lee.sharding.entity.dto.UserDTO;
import com.lee.sharding.entity.param.UserParam;
import com.lee.sharding.mapper.UserMapper;
import com.lee.sharding.service.UserService;
import com.lee.sharding.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Lee
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<UserDTO> list(UserParam param) {

        LambdaQueryWrapper<User> queue = new LambdaQueryWrapper<>();
        queue.ge(User::getCreateDate, param.getStartDate());
        queue.le(User::getCreateDate, param.getEndDate());

        List<User> list = this.list(queue);
        List<UserDTO> voList = new ArrayList<>();

        for (User user : list) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            voList.add(dto);
        }

        return voList;
    }

    @Override
    public boolean save(UserDTO dto) {

        if (Objects.isNull(dto.getCreateDate())) {
            dto.setCreateDate(new Date());
        }

        User user = new User();
        BeanUtils.copyProperties(dto, user);

        return super.save(user);
    }

    @Override
    public UserDTO getOne(UserParam param) {

        LambdaQueryWrapper<User> queue = getOneUserQuery(param.getCreateDate(), param.getId());
        queue.last("LIMIT 1");

        User user = this.getOne(queue);
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public void update(UserDTO dto) {

        LambdaQueryWrapper<User> queue = getOneUserQuery(dto.getCreateDate(), dto.getId());

        User user = new User();
        BeanUtils.copyProperties(dto, user);

        this.update(user, queue);
    }

    @NotNull
    private static LambdaQueryWrapper<User> getOneUserQuery(Date createDate, Long userId) {
        LambdaQueryWrapper<User> queue = new LambdaQueryWrapper<>();
        queue.gt(User::getCreateDate, DateUtil.getMonthFirstDay(createDate));
        queue.le(User::getCreateDate, DateUtil.getMonthLastDay(createDate));
        queue.eq(User::getId, userId);
        return queue;
    }
}
