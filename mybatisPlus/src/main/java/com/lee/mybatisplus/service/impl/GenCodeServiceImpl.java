package com.lee.mybatisplus.service.impl;

import com.lee.mybatisplus.domain.dao.Table;
import com.lee.mybatisplus.domain.dao.TableColumn;
import com.lee.mybatisplus.domain.request.GenCodeRequest;
import com.lee.mybatisplus.mapper.GenCodeMapper;
import com.lee.mybatisplus.service.GenCodeService;
import com.lee.mybatisplus.utils.GenUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Service
public class GenCodeServiceImpl implements GenCodeService {

    @Resource
    private GenCodeMapper genCodeMapper;

    @Override
    public byte[] genCode(GenCodeRequest genCodeRequest) {

        // 表信息
        Table tableInfo = genCodeMapper.getTableInfo(genCodeRequest.getTableName());
        // 列信息
        List<TableColumn> tableColumnsInfo = genCodeMapper.getTableColumnsInfo(genCodeRequest.getTableName());
        // zip输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 生成代码
        GenUtil.genCodeToZip( tableInfo, tableColumnsInfo, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();

    }
}
