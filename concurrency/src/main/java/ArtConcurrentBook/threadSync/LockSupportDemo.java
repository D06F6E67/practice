package ArtConcurrentBook.threadSync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程同步 LockSupport
 *
 * @author Lee
 */
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();
        Thread threadB = new Thread(() -> {
            if (list.size() != 4) {
                LockSupport.park();
                System.out.println("B: list长度:" + list.size() + " " + System.currentTimeMillis());
            }
        });

        Thread threadA = new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                list.add("A:" + i);
                System.out.println("A: list长度:" + list.size());
                if (list.size() == 4) {
                    System.out.println("A 修改 ture" + System.currentTimeMillis());
                    LockSupport.unpark(threadB);
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
