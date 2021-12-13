package com.lee.mybatis.mapper;

import com.lee.mybatis.domain.SysPrivilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * 权限表
 */
@Mapper
public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class, method = "selectById2")
    SysPrivilege selectById(Long id);
}
