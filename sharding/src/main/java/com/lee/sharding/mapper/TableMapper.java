package com.lee.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;

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
    Integer existsTable(String tableName);

    /**
     * 创建用户表
     *
     * @param name 表名
     */
    void createUser(String name);

    /**
     * 创建信息表
     *
     * @param name 表名
     */
    void createInfo(String name);
}
