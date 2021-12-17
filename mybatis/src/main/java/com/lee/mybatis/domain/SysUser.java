package com.lee.mybatis.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户表
 */
@Data
public class SysUser {

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 简介
     */
    private String userInfo;
    /**
     * 头像
     */
    private byte[] headImg;
    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 角色信息
     */
    private SysRole role;

    /**
     * 用户角色集合(一对多使用)
     */
    private List<SysRole> roleList;

}
