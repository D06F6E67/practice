package structure.adapter;

/**
 * 适配器模式(类适配器)
 *
 * @author Lee
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}

/**
 * 目标接口
 */
interface Target {
    void request();
}

/**
 * 适配者接口
 */
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者代码被调用");
    }
}

/**
 * 类适配器类
 */
class ClassAdapterTest{
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();
    }
}