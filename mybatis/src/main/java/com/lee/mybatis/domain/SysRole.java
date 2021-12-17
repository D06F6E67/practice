package com.lee.mybatis.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 角色表
 */
@Data
public class SysRole {

    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 有效标志
     */
    private Integer enabled;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户信息 (测试使用)
     */
    private SysUser user;

    /**
     * 角色包含的权限(一对多)
     */
    private List<SysPrivilege> privilegeList;

}
