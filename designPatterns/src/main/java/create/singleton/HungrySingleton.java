package create.singleton;

/**
 * 单例 饿汉
 *
 * @author Lee
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
        System.out.println("创建");
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    public void getName() {
        System.out.println("名称");
    }
}

class HungrySingletonTest {
    public static void main(String[] args) {
        HungrySingleton h1 = HungrySingleton.getInstance();
        h1.getName();
        HungrySingleton h2 = HungrySingleton.getInstance();
        h2.getName();
        System.out.println(h1 == h2);
        System.out.println(h1.equals(h2));
    }
}