package com.lee.mybatisplus.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lee.mybatisplus.config.GenConfig;
import com.lee.mybatisplus.domain.dao.Table;
import com.lee.mybatisplus.domain.dao.TableColumn;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 模板处理工具类
 *
 * @author ruoyi
 */
public class VelocityUtils {
    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 默认上级菜单，系统工具
     */
    private static final String DEFAULT_PARENT_MENU_ID = "3";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(Table table) {
        String moduleName = GenConfig.getModuleName();
        String businessName = StringUtil.toCamelCase(table.getTableName());
        String packageName = GenConfig.getPackageName();
        String functionName = table.getTableComment();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName" , table.getTableName());
        velocityContext.put("functionName" , StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName" , table.getClassName());
        velocityContext.put("className" , StringUtils.uncapitalize(table.getClassName()));
        velocityContext.put("moduleName" , moduleName);
        velocityContext.put("BusinessName" , StringUtils.capitalize(businessName));
        velocityContext.put("businessName" , businessName);
        velocityContext.put("basePackage" , getPackagePrefix(packageName));
        velocityContext.put("packageName" , packageName);
        velocityContext.put("author" , GenConfig.getAuthor());
        velocityContext.put("datetime" , DateUtils.getDate());
        velocityContext.put("pkColumn" , table.getPkColumn());
        velocityContext.put("importList" , getImportList(table));
        velocityContext.put("permissionPrefix" , getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns" , table.getColumns());
        velocityContext.put("table" , table);
        velocityContext.put("baseUrl" , StrUtil.replace(table.getTableName(), "_" , "/"));
        setMenuVelocityContext(velocityContext, table);
        return velocityContext;
    }

    public static void setMenuVelocityContext(VelocityContext context, Table table) {
        String options = table.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String parentMenuId = getParentMenuId(paramsObj);
        context.put("parentMenuId" , parentMenuId);
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList() {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/vo.java.vm");
        templates.add("vm/java/bo.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        templates.add("vm/sql/sql.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, Table table) {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = GenConfig.getPackageName();
        // 模块名
        String moduleName = GenConfig.getModuleName();
        // 大写类名
        String className = table.getClassName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, "." , "/");
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;

        if (template.contains("domain.java.vm")) {
            fileName = StringUtil.format("{}/domain/{}.java" , javaPath, className);
        } else if (template.contains("vo.java.vm")) {
            fileName = StringUtil.format("{}/domain/vo/{}Vo.java" , javaPath, className);
        } else if (template.contains("bo.java.vm")) {
            fileName = StringUtil.format("{}/domain/bo/{}Bo.java" , javaPath, className);
        } else if (template.contains("mapper.java.vm")) {
            fileName = StringUtil.format("{}/mapper/{}Mapper.java" , javaPath, className);
        } else if (template.contains("service.java.vm")) {
            fileName = StringUtil.format("{}/service/I{}Service.java" , javaPath, className);
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName = StringUtil.format("{}/service/impl/{}ServiceImpl.java" , javaPath, className);
        } else if (template.contains("controller.java.vm")) {
            fileName = StringUtil.format("{}/controller/{}Controller.java" , javaPath, className);
        } else if (template.contains("mapper.xml.vm")) {
            fileName = StringUtil.format("{}/{}Mapper.xml" , mybatisPath, className);
        } else if (template.contains("sql.vm")) {
            fileName = className + "Menu.sql";
        }
        return fileName;
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        String basePackage = StringUtils.substring(packageName, 0, lastIndex);
        return basePackage;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param table 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(Table table) {
        List<TableColumn> columns = table.getColumns();
        HashSet<String> importList = new HashSet<String>();
        for (TableColumn column : columns) {
            if (!column.isSuperColumn() && "Date".equals(column.getJavaType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (!column.isSuperColumn() && "BigDecimal".equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName 模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName)
    {
        return StringUtil.format("{}:{}", moduleName, businessName);
    }

    /**
     * 获取上级菜单ID字段
     *
     * @param paramsObj 生成其他选项
     * @return 上级菜单ID字段
     */
    public static String getParentMenuId(JSONObject paramsObj) {
        /** 上级菜单ID字段 */
        String parentMenuId = "parentMenuId";
        if (StringUtil.isNotEmpty(paramsObj) && paramsObj.containsKey(parentMenuId)
                && StringUtils.isNotEmpty(paramsObj.getString(parentMenuId))) {
            return paramsObj.getString(parentMenuId);
        }
        return DEFAULT_PARENT_MENU_ID;
    }
}
