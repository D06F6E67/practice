package com.lee.mybatisplus.domain.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 表属性
 */
@Data
public class Table {

    /**
     * 数据表登记目录
     */
    private String tableCatalog;

    /**
     * 数据表所属的数据库名
     */
    private String tableSchema;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表类型[system view|base table]
     */
    private String tableType;

    /**
     * 使用的数据库引擎[MyISAM|CSV|InnoDB]
     */
    private String engine;

    /**
     * 版本，默认值10
     */
    private Integer version;

    /**
     * 行格式[Compact|Dynamic|Fixed]
     */
    private String rowFormat;

    /**
     * 表里所存多少行数据
     */
    private Integer tableRows;

    /**
     * 平均行长度
     */
    private Integer avgRowLength;

    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 最大数据长度
     */
    private Integer maxDataLength;

    /**
     * 索引长度
     */
    private Integer indexLength;

    /**
     * 空间碎片
     */
    private Integer dataFree;

    /**
     * 做自增主键的自动增量当前值
     */
    private Integer autoIncrement;

    /**
     * 表的创建时间
     */
    private Date createTime;

    /**
     * 表的更新时间
     */
    private Date updateTime;

    /**
     * 表的检查时间
     */
    private Date checkTime;

    /**
     * 表的字符校验编码集
     */
    private String tableCollation;

    /**
     * 校验和
     */
    private Integer checksum;

    /**
     * 创建选项
     */
    private String createOptions;

    /**
     * 表备注
     */
    private String tableComment;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 非数据库字段 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 实体类名称(首字母大写)
     */
    @TableField(exist = false)
    private String className;

    /**
     * 主键信息
     */
    @TableField(exist = false)
    private TableColumn pkColumn;

    /**
     * 表列信息
     */
    @TableField(exist = false)
    private List<TableColumn> columns;

    /**
     * 其它生成选项
     */
    @TableField(exist = false)
    private String options;

    /**
     * 上级菜单ID字段
     */
    @TableField(exist = false)
    private String parentMenuId;


}
