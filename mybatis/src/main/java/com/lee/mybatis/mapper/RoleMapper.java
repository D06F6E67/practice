package com.lee.mybatis.mapper;

import com.lee.mybatis.domain.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色表
 */
@Mapper
public interface RoleMapper {

    /**
     * 通过用户id获取用户角色
     *
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 通过用户id获取用户角色
     *
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRoleByUserId2(Long userId);

    /**
     * 通过用户名和状态获取用户角色
     *
     * @param userId  用户id
     * @param enabled 有效标识
     * @return
     */
    List<SysRole> selectRoleByUserIdAndEnabled(Long userId, Integer enabled);

    /**
     * 通过id获取角色
     *
     * @param id
     * @return
     */
    @Select({"SELECT id, role_name roleName, enabled, create_by createBy, create_time createTime ",
            "FROM sys_role ",
            "WHERE id = #{id} "})
    SysRole selectById(Long id);

    /**
     * 通过id获取角色  mybatis 3.3.0只有这种@Results方法不再支持，改为下面selectById3中的方法
     *
     * @param id
     * @return
     */
    // @Results({
    //         @Result(property = "id", column = "id", id = true),
    //         @Result(property = "role_name", column = "roleName"),
    //         @Result(property = "enabled", column = "enabled"),
    //         @Result(property = "create_by", column = "createBy"),
    //         @Result(property = "create_time", column = "createTime")
    // })
    @Select({"SELECT id, role_name, enabled, create_by, create_time ",
            "FROM sys_role ",
            "WHERE id = #{id} "})
    // @ResultMap("roleResultMap")
    SysRole selectById2(Long id);

    /**
     * 通过id获取角色
     *
     * @return
     */
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "role_name", column = "roleName"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "create_by", column = "createBy"),
            @Result(property = "create_time", column = "createTime")
    })
    @Select({"SELECT * FROM sys_role "})
    // @ResultMap("roleResultMap")
    List<SysRole> selectAll();


    @Insert({"INSERT INTO sys_role(id, role_name, enabled, create_by, create_time)",
            "VALUES (#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime,jdbcType=TIMESTAMP} )"})
    int insert(SysRole sysRole);

    @Insert({"INSERT INTO sys_role(id, role_name, enabled, create_by, create_time)",
            "VALUES (#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime,jdbcType=TIMESTAMP} )"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    @Insert({"INSERT INTO sys_role(id, role_name, enabled, create_by, create_time)",
            "VALUES (#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime,jdbcType=TIMESTAMP} )"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",
            keyProperty = "id",
            resultType = Long.class,
            before = false)
    int insert3(SysRole sysRole);
}
