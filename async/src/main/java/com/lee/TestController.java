package com.lee;

import com.lee.async.AsyncTasks;
import com.lee.async.AsyncVo;
import com.lee.async.RequestQueue;
import com.lee.async.TestTask;
import com.lee.dynamic.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 模拟下单处理，实现高吞吐量异步处理请求
 *
 *  1、 Controller层接口只接收请求，不进行处理，而是把请求信息放入到对应该接口的请求队列中
 *  2、 该接口对应的任务类监听对应接口的请求队列，从队列中顺序取出请求信息并进行处理
 *
 *  优点：接口几乎在收到请求的同时就已经返回，处理程序在后台异步进行处理，大大提高吞吐量
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private RequestQueue queue;
    @Autowired
    private AsyncTasks asyncTasks;
    @Autowired
    private TestTask testTask;
    @Autowired
    private Task task;

    @GetMapping("/order/{number}")
    public DeferredResult<Object> order(@PathVariable("number") String number) throws InterruptedException {
        System.out.println("[ OrderController ] 接到下单请求");
        System.out.println("当前待处理订单数： " + queue.getOrderQueue().size());

        AsyncVo<String, Object> vo = new AsyncVo<String, Object>();
        DeferredResult<Object> result = new DeferredResult<Object>();

        vo.setParams(number);
        vo.setResult(result);

        queue.getOrderQueue().put(vo);
        System.out.println("[ OrderController ] 返回下单结果");
        return result;
    }

    // @Async
    @GetMapping("/test/{number}")
    public DeferredResult<Object> test(@PathVariable("number") String number) {
        log.info("test number = {}", number);
        AsyncVo<String, Object> vo = new AsyncVo<String, Object>();
        DeferredResult<Object> result = new DeferredResult<Object>();

        vo.setParams(number);
        vo.setResult(result);
        asyncTasks.test(vo);
        log.info("返回结果");
        return result;
    }

    @GetMapping("/test")
    public String test() throws Exception {
        log.info("收到请求");
        asyncTasks.doTaskOne();
        log.info("返回结果");
        return "ok";
    }

    @GetMapping("/task/{number}")
    public Integer task(@PathVariable("number") Integer number){
        task.setNumber(number);
        return task.getNumber();
    }

    @GetMapping("/runing/{status}")
    public Boolean running(@PathVariable("status") Boolean status) {
        testTask.setRunning(status);
        return testTask.isRunning();
    }

}
