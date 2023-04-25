package com.lee.sharding.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Lee
 */
@Data
@TableName("info")
public class Info {

    public final static String TABLE_NAME = "info";

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private String value;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}