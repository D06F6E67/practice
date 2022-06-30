package com.lee.sharrding.service.impl;

import com.lee.sharrding.entity.User;
import com.lee.sharrding.mapper.TableMapper;
import com.lee.sharrding.service.TableService;
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
