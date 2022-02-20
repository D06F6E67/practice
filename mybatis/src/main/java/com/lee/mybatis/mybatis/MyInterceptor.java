package com.lee.mybatis.mybatis;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * mybatis拦截器接口
 */
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    /**
     * 用来传递插件的参数
     * 在配置拦截器时，plugin的interceptor属性为拦截器实现类的全限定名称，
     * 如果需要参数，可以在plugin标签通过property进行配置
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
