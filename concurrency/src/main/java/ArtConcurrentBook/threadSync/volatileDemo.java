package ArtConcurrentBook.threadSync;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程同步 volatile
 *
 * @author Lee
 */
public class volatileDemo {
    static volatile Boolean notice = false;

    public static void main(String[] args) throws InterruptedException {


        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                list.add("A:" + i);
                System.out.println("A: list长度:" + list.size());
                if (list.size() == 4) {
                    System.out.println("A 修改 ture" + System.currentTimeMillis());
                    notice = true;
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

                if (notice) {
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
