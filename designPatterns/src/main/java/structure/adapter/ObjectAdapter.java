package structure.adapter;

/**
 * 适配器模式(对象配器)
 *
 * @author Lee
 */
public class ObjectAdapter implements Target{
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

class ObjectAdapterTest {
    public static void main(String[] args) {
        Target target = new ObjectAdapter(new Adaptee());
        target.request();
    }
}
