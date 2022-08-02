package com.lee.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.sharding.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lee
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
