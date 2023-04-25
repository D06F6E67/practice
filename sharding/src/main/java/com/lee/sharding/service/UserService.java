package com.lee.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.sharding.entity.User;
import com.lee.sharding.entity.dto.UserDTO;
import com.lee.sharding.entity.param.UserParam;

import java.util.List;

/**
 * @author Lee
 */
public interface UserService extends IService<User> {

    /**
     * 列表
     *
     * @param param 参数
     * @return 结果
     */
    List<UserDTO> list(UserParam param);

    /**
     * 保存
     *
     * @param dto 信息
     * @return 结果
     */
    boolean save(UserDTO dto);

    /**
     * 获取一个
     *
     * @param param ID和时间
     * @return 用户信息
     */
    UserDTO getOne(UserParam param);

    /**
     * 修改
     *
     * @param dto 信息
     */
    void update(UserDTO dto);
}
