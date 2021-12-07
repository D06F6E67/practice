package com.lee.mybatisplus.mapper;

import com.lee.mybatisplus.domain.dao.Table;
import com.lee.mybatisplus.domain.dao.TableColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenCodeMapper {

    /**
     * 获取表信息
     * @param tableName 表名
     * @return 表信息Map
     */
    Table getTableInfo(String tableName);

    /**
     * 获取列信息
     * @param tableName 表名
     * @return 列信息List
     */
    List<TableColumn> getTableColumnsInfo (String tableName);

}
