package create.singleton;

/**
 * 单例 懒汉
 *
 * @author Lee
 */
public class LazySingleton {

    private static volatile LazySingleton instance = null;    //保证 instance 在所有线程中同步

    private LazySingleton() {
        System.out.println("创建");
    }    //private 避免类在外部被实例化

    public static synchronized LazySingleton getInstance() {
        //getInstance 方法前加同步
        if (instance == null)
            instance = new LazySingleton();
        else
            System.out.println("已经存在");

        return instance;
    }

    public void getName() {
        System.out.println("名称");
    }

}

class LazySingletonTest {
    public static void main(String[] args) {
        LazySingleton l1 = LazySingleton.getInstance();
        l1.getName();
        LazySingleton l2 = LazySingleton.getInstance();
        l2.getName();
        System.out.println(l1 == l2);
        System.out.println(l1.equals(l2));
    }
}
