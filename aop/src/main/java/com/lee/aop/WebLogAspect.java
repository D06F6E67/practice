package com.lee.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口请求日志
 */
// @Aspect
// @Component
// @Slf4j
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
//    private final static Logger logger = LoggerFactory.getLogger("API_REQUEST_LOG");

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
//    @Pointcut("execution(public * com.zdjs.cdb.api.app.*..*(..))")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void webLog() {
    }


    /**
     * 环绕
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        try{
            logger.info("========================================== Start ==========================================");
            // 开始打印请求日志
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                // 打印请求 url
                logger.info("URL            : {}", request.getRequestURL().toString());
                // 打印 Http method
                logger.info("HTTP Method    : {}", request.getMethod());
                // 打印调用 controller 的全路径以及执行方法
                logger.info("Class Method   : {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
                // 打印请求的 IP
                logger.info("IP             : {}", request.getRemoteAddr());
                // 打印请求入参
                try {
                    logger.info("Request Args   : {}", proceedingJoinPoint.getArgs()!=null?JSON.toJSON(proceedingJoinPoint.getArgs()):"{ }");
                } catch (JSONException e) {
                    logger.info("Request Args   : {}", proceedingJoinPoint.getArgs());
                }
            }
            // 打印出参
            logger.info("Response Args  : {}", JSON.toJSON(result));
            // 执行耗时
            logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
            logger.info("=========================================== End ===========================================");
            // 每个请求之间空一行
            logger.info("");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
