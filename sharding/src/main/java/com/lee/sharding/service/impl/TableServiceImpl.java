package com.lee.sharding.service.impl;

import com.lee.sharding.entity.User;
import com.lee.sharding.mapper.TableMapper;
import com.lee.sharding.service.TableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lee
 */
@Service
public class TableServiceImpl implements TableService {

    @Resource
    private TableMapper tableMapper;

    @Override
    public Boolean existsTable(String tableName) {
        return 0 != tableMapper.existsTable(tableName);
    }

    @Override
    public void createTable(String tableName) {
        int i = tableName.lastIndexOf("_");
        if (User.TABLE_NAME.equals(tableName.substring(0, i))) {
            tableMapper.createUser(tableName);
        }
    }

}
