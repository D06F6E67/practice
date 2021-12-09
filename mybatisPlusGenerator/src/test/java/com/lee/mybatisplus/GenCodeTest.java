package com.lee.mybatisplus;

import com.lee.mybatisplus.domain.dao.Table;
import com.lee.mybatisplus.domain.dao.TableColumn;
import com.lee.mybatisplus.domain.request.GenCodeRequest;
import com.lee.mybatisplus.mapper.GenCodeMapper;
import com.lee.mybatisplus.service.GenCodeService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenCodeTest {
    @Autowired
    GenCodeMapper genCodeMapper;
    @Autowired
    GenCodeService genCodeService;

    @Test
    public void table() {
        Table tableInfo = genCodeMapper.getTableInfo("user");
        System.out.println(tableInfo.toString());
        List<TableColumn> userList = genCodeMapper.getTableColumnsInfo("user");
        userList.forEach( user ->
                System.out.println(user.toString()));
    }

    @Test
    public void code() throws IOException {
        GenCodeRequest request = new GenCodeRequest();
        request.setTableName("user");
        byte[] bytes = genCodeService.genCode(request);
        OutputStream os = new FileOutputStream("D:/user.zip");
        IOUtils.write(bytes, os);
    }
}
