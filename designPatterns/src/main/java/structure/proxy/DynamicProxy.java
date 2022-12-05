package structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理模式(动态代理)
 *
 * @author Lee
 */
public class DynamicProxy<T> {

    public T getProxy(Object target) throws Exception {
        Class<T> proxyClass = (Class<T>) Proxy.getProxyClass(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces());

        return proxyClass.getConstructor(InvocationHandler.class).newInstance(
                (InvocationHandler) (proxy, method, args) -> {
                    preRequest();
                    Object invoke = method.invoke(target, args);
                    postRequest();
                    return invoke;
                });
    }

    private void preRequest() {
        System.out.println("前预处理");
    }

    private void postRequest() {
        System.out.println("后续处理");
    }
}

/**
 * 真实主题
 */
class RealSubject1 implements Subject {
    @Override
    public void Request() {
        System.out.println("真实主题1方法");
    }
}

/**
 * 真实主题
 */
class RealSubject2 implements Subject {
    @Override
    public void Request() {
        System.out.println("真实主题2方法");
    }
}

class DynamicProxyTest {
    public static void main(String[] args) throws Exception {
        DynamicProxy<Subject> proxyClass = new DynamicProxy<>();
        Subject proxy1 = proxyClass.getProxy(new RealSubject1());
        proxy1.Request();

        Subject proxy2 = proxyClass.getProxy(new RealSubject2());
        proxy2.Request();

    }
}