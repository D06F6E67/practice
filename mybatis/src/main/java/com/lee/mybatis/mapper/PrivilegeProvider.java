package com.lee.mybatis.mapper;

import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {
    public String selectById(final Long id) {
        return new SQL(){
            {
                SELECT("id, privilege_name, privilege_url");
                FROM("sys_privilege");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String selectById2(final Long id) {
        return "SELECT id, privilege_name, privilege_url FROM sys_privilege WHERE id = #{id}";
    }
}
