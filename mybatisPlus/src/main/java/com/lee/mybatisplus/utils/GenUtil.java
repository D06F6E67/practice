package com.lee.mybatisplus.utils;

import com.lee.mybatisplus.constants.Constants;
import com.lee.mybatisplus.constants.GenConstants;
import com.lee.mybatisplus.domain.dao.Table;
import com.lee.mybatisplus.domain.dao.TableColumn;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成工具类
 */
public class GenUtil {

    /**
     * 生成代码到zip
     *
     * @param table       表信息
     * @param columnList  列信息
     * @param zip         zip数据流
     */
    public static void genCodeToZip(Table table, List<TableColumn> columnList, ZipOutputStream zip) {
        // 表名转换成Java类名
        table.setClassName(StringUtil.tableNameToJavaName(table.getTableName()));
        // 设置列信息
        setColumn(table, columnList);
        initVelocity();
        // 设置模板信息
        VelocityContext context = VelocityUtils.prepareContext(table);
        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                System.out.println("渲染模板失败，表名：" + table.getTableName() + e.getMessage());
            }
        }

    }

    /**
     * 设置列信息
     *
     * @param table      业务表信息
     * @param columnList 列信息
     */
    public static void setColumn(Table table, List<TableColumn> columnList) {
        for (TableColumn column : columnList) {
            if ("NO".equals(column.getIsNullable()) && "PRI".equals(column.getColumnKey())) {
                table.setPkColumn(column);
                column.setIsPk(true);
                break;
            }
        }
        if (Objects.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        table.setColumns(columnList);
        columnList.forEach(GTC -> initColumnField(GTC));
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(TableColumn column) {
        String dataType = column.getDataType(); // 数据类型
        String columnName = column.getColumnName(); // 列名
        // 设置java字段名
        column.setJavaField(StringUtil.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(GenConstants.TYPE_STRING);

        if (StringUtil.arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
            column.setJavaType(GenConstants.TYPE_DATE);
        } else if (StringUtil.arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            Integer numericPrecision = column.getNumericPrecision();
            Integer numericScale = column.getNumericScale();
            // 如果是浮点型 统一用BigDecimal
            if (numericScale != null && numericScale != 0) {
                column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (numericPrecision != null && numericPrecision < 11) {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }
    }

    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
