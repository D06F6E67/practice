package com.lee.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lee
 */
@Mapper
public interface TableMapper {

    /**
     * 验证表
     *
     * @param tableName 表名
     * @return 存在返回表明，不存在返回空
     */
    Integer existsTable(@Param("tableName") String tableName);

    /**
     * 创建表
     *
     * @param name 表后缀
     */
    void createUser(@Param("name") String name);
}
