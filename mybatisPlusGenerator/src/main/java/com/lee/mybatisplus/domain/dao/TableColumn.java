package com.lee.mybatisplus.domain.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lee.mybatisplus.constants.GenConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 列属性
 */
@Data
public class TableColumn {
    /**
     * 表限定符
     */
    private String tableCatalog;
    /**
     * 表所有者
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列标识号
     *
     * 其实就是字段编号，从1开始往后排
     */
    private Integer ordinalPosition;
    /**
     * 列的默认值
     */
    private String columnDefault;
    /**
     * 字段是否可以是NULL
     *
     * 该列记录的值是YES或者NO
     */
    private String isNullable;
    /**
     * 系统提供的数据类型
     *
     * 里面的值是字符串，比如varchar，float，int
     */
    private String dataType;
    /**
     * 字段的最大字符数。
     *
     * 假如字段设置为varchar(50)，那么这一列记录的值就是50。
     *
     * 该列只适用于二进制数据，字符，文本，图像数据。其他类型数据比如int，float，datetime等，在该列显示为NULL。
     */
    private Integer characterMaximumLength;
    /**
     * 字段的最大字节数。
     *
     * 和最大字符数一样，只适用于二进制数据，字符，文本，图像数据，其他类型显示为NULL。
     *
     * 和最大字符数的数值有比例关系，和字符集有关。比如UTF8类型的表，最大字节数就是最大字符数的3倍。
     */
    private Integer characterOctetLength;
    /**
     * 数字精度。
     *
     * 适用于各种数字类型比如int，float之类的。
     *
     * 如果字段设置为int(10)，那么在该列保存的数值是9，少一位，还没有研究原因。
     *
     * 如果字段设置为float(10,3)，那么在该列报错的数值是10。
     *
     * 非数字类型显示为在该列NULL。
     */
    private Integer numericPrecision;
    /**
     * 小数位数。
     *
     * 和数字精度一样，适用于各种数字类型比如int，float之类。
     *
     * 如果字段设置为int(10)，那么在该列保存的数值是0，代表没有小数。
     *
     * 如果字段设置为float(10,3)，那么在该列报错的数值是3。
     *
     * 非数字类型显示为在该列NULL。
     */
    private Integer numericScale;
    /**
     * datetime 及 SQL-92 interval 数据类型的子类型代码。对于其它数据类型，返回 NULL。
     */
    private Integer datetimePrecision;
    /**
     * 如果该列是字符数据或 text 数据类型，那么为字符集返回唯一的名称。否则，返回 NULL。
     */
    private String characterSetName;
    /**
     * 如果列是字符数据或 text 数据类型，那么为排序次序返回唯一的名称。否则，返回 NULL。
     */
    private String collationName;
    /**
     * 字段类型。比如float(9,3)，varchar(50)
     */
    private String columnType;
    /**
     * 索引类型。
     *
     * 可包含的值有PRI，代表主键，UNI，代表唯一键，MUL，可重复。
     */
    private String columnKey;
    /**
     * 其他信息。
     *
     * 比如主键的auto_increment。
     */
    private String extra;
    /**
     * 权限
     *
     * 多个权限用逗号隔开，比如 select,insert,update,references
     */
    private String privileges;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 组合字段的公式
     *
     * http://blog.csdn.net/lkforce/article/details/79557373
     */
    private String GENERATION_EXPRESSION;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 非数据库字段 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * JAVA类型
     */
    @TableField(exist = false)
    private String javaType;

    /**
     * JAVA字段名
     */
    @TableField(exist = false)
    private String javaField;

    /**
     * 是否是主键 1是
     */
    @TableField(exist = false)
    private Boolean isPk;

    /**
     * 是否是超类中的字段
     */
    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

}
