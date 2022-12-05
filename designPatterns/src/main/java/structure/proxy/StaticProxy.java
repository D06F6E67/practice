package structure.proxy;

/**
 * 代理模式(静态代理)
 *
 * @author Lee
 */
public class StaticProxy implements Subject {

    private RealSubject realSubject;

    @Override
    public void Request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }

    private void preRequest() {
        System.out.println("前预处理");
    }

    private void postRequest() {
        System.out.println("后续处理");
    }
}

/**
 * 抽象主题
 */
interface Subject {
    void Request();
}

/**
 * 真实主题
 */
class RealSubject implements Subject {
    @Override
    public void Request() {
        System.out.println("真实主题方法");
    }
}

class StaticProxyTest {
    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy();
        proxy.Request();
    }
}
