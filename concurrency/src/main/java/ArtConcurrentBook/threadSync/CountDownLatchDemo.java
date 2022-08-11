package ArtConcurrentBook.threadSync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 线程同步 CountDownLatch
 *
 * @author Lee
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);


        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                list.add("A:" + i);
                System.out.println("A: list长度:" + list.size());
                if (list.size() == 4) {
                    System.out.println("A 修改 ture" + System.currentTimeMillis());
                    countDownLatch.countDown();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread threadB = new Thread(() -> {
            while (true) {

                if (list.size() != 4) {

                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("B: list长度:" + list.size() + " " + System.currentTimeMillis());
                    break;
                }
            }
        });


        threadB.start();

        Thread.sleep(1000);

        threadA.start();
    }
}
