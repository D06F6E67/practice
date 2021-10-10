package com.lee.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共返回值类型
 */
@Data
public class R implements Serializable {

    private Integer code;
    private JSONObject data;

    private R(Integer code, JSONObject data) {
        this.code = code;
        this.data = data;
    }

    public static R Source(Object data) {
        return new R(200, JSON.parseObject(JSON.toJSONString(data)));
    }
}
