package ArtConcurrentBook.threadSync;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程同步 synchronized
 *
 * @author Lee
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            synchronized (lock) {

                for (int i = 0; i <= 5; i++) {
                    list.add("A:" + i);
                    System.out.println("A: list长度:" + list.size());
                    if (list.size() == 4) {
                        System.out.println("A 唤醒 B");
                        lock.notifyAll();
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (lock) {

                    if (list.size() != 4) {
                        try {
                            System.out.println("B 休眠");
                            lock.wait();

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("B: list长度:" + list.size());
                        break;
                    }
                }
            }
        });


        threadB.start();

        Thread.sleep(1000);

        threadA.start();
    }
}
