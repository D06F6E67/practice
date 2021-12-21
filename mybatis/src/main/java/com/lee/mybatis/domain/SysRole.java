package com.lee.mybatis.domain;

import com.lee.mybatis.enums.Enabled;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 */
@Data
// 因为Mybatis二级缓存通过序列化的方式实现读写缓存类，所以需要实现序列化接口
public class SysRole implements Serializable {

    private static final long serialVersionUID = 8123346412839602476L;

    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 有效标志(使用枚举)
     */
    private Enabled enabled;
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
