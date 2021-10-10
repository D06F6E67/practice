package com.lee.aop.encryption;

import com.alibaba.fastjson.JSONObject;
import com.lee.aop.R;
import com.lee.aop.utils.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 加密切面
 */
@Aspect
@Component
public class EncryAspect {

    @Pointcut("@annotation(com.lee.aop.encryption.Encry)")
    public void EncryAspect() {}

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @Before("EncryAspect()")
    public void before(JoinPoint joinPoint) {
        System.out.println("进入方法前执行.....");
    }

    /**
     * 环绕通知
     * */
    @Around("EncryAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //得到目标方法
        Method method = signature.getMethod();
        //得到方法之上的注解
        Encry encry = method.getAnnotation(Encry.class);
        //注解业务判断
        R r = (R)joinPoint.proceed(); // 获取返回的对象
        JSONObject data = r.getData(); // 获取返回数据
        String s = data.get(encry.value()).toString();
        s = StringUtil.hide(s); // 脱敏处理
        data.put(encry.value(),s);
        return r;
    }


    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "EncryAspect()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("EncryAspect()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("EncryAspect()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }




}
