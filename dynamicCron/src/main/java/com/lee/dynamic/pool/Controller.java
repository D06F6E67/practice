package com.lee.dynamic.pool;

import com.lee.dynamic.pool.bean.TaskBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务 controller
 */
@RestController
@RequestMapping("/task")
public class Controller {
    @Autowired
    private Service service;

    /**
     * 添加任务
     */
    @PostMapping("/add")
    public String add(@RequestBody TaskBean bean) {
        return service.add(bean);
    }

    /**
     * 所有任务列表
     */
    @GetMapping("/list")
    public List<TaskBean> taskList() {
        return service.taskList();
    }

    /**
     * 根据任务key => 启动任务
     */
    @GetMapping("/start/{key}")
    public String start(@PathVariable("key") String taskKey) {
        service.start(taskKey);
        return "start success";
    }

    /**
     * 根据任务key => 停止任务
     */
    @RequestMapping("/stop/{key}")
    public String stop(@PathVariable("key") String taskKey) {
        service.stop(taskKey);
        return "stop success";
    }

    /**
     * 根据任务key => 重启任务
     */
    @RequestMapping("/restart/{key}")
    public String restart(@PathVariable("key") String taskKey) {
        service.restart(taskKey);
        return "restart success";
    }
}
