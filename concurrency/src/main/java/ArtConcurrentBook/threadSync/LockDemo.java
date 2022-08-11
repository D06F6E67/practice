package ArtConcurrentBook.threadSync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步 Lock和Condition
 *
 * @author Lee
 */
public class LockDemo {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 0; i <= 5; i++) {
                list.add("A:" + i);
                System.out.println("A: list长度:" + list.size());
                if (list.size() == 4) {
                    System.out.println("A 唤醒 B");
                    condition.signalAll();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.unlock();
        });


        Thread threadB = new Thread(() -> {
            while (true) {

                lock.lock();
                if (list.size() != 4) {
                    try {
                        System.out.println("B 休眠");
                        condition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("B: list长度:" + list.size());
                    break;
                }
                lock.unlock();
            }
        });


        threadB.start();

        Thread.sleep(1000);

        threadA.start();
    }
}
