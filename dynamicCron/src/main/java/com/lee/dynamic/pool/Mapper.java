package com.lee.dynamic.pool;

import com.lee.dynamic.pool.bean.TaskBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务表 mapper
 */
@Component
public class Mapper {
    // 使用map 模拟数据库
    Map<String, TaskBean> data = new HashMap<>();

    /**
     * 根据key 获取 任务信息
     */
    TaskBean getByKey(String key) {
        return data.get(key);
    }

    /**
     * 获取程序初始化需要自启的任务信息
     */
    List<TaskBean> getAllNeedStartTask() {
        List<TaskBean> beanList = new ArrayList<>();
        for (TaskBean bean : data.values()) {
            if (1 == bean.getInitStartFlag())
                beanList.add(bean);
        }
        return beanList;
    }

    /**
     * 获取所有任务
     */
    List<TaskBean> getAllTask() {
        List<TaskBean> beanList = new ArrayList<>();
        for (String key: data.keySet()) {
            beanList.add(data.get(key));
        }
        return beanList;
    }

    /**
     * 添加任务
     */
    public String add(TaskBean bean) {
        data.put(bean.getTaskKey(), bean);
        return bean.getTaskKey();
    }
}
