package com.lee.mybatis.domain;

import lombok.Data;

/**
 * 权限表
 */
@Data
public class SysPrivilege {

    /**
     * 权限ID
     */
    private Long id;
    /**
     * 权限名称
     */
    private String privilegeName;
    /**
     * 权限URL
     */
    private String privilegeUrl;

}
