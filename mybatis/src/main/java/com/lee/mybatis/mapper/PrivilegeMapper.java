package com.lee.mybatis.mapper;

import com.lee.mybatis.domain.SysPrivilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 权限表
 */
@Mapper
public interface PrivilegeMapper {

    /**
     * 使用java方法返回SQL语句
     * @SelectProvider @InsertProvider @UpdateProvider @DeleteProvider
     * type: 提供SQL语句的java类
     * method: java类中提供SQL语句的方法
     */
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById2")
    SysPrivilege selectById(Long id);

    /**
     * 通过角色id获取权限
     * @param roleId
     * @return
     */
    List<SysPrivilege> selectPrivilegeBuRoleId(Long roleId);
}
