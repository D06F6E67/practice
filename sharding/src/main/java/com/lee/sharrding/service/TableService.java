package com.lee.sharrding.service;

/**
 * 表
 *
 * @author Lee
 */
public interface TableService {


    /**
     * 表是否存在
     *
     * @param tableName 表名
     * @return 结果
     */
    Boolean existsTable(String tableName);

    /**
     * 创建表，应先验证是否存在再创建
     *
     * @param tableName 表名
     */
    void createTable(String tableName);

}
