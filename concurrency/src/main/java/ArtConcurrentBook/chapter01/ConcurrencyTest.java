package ArtConcurrentBook.chapter01;

/**
 * 多线程一定快吗？ 不一定
 * 并发和单线程执行测试
 * 结果：循环次数  串行耗时ms  并行耗时ms
 *       1亿      130         77
 *       1千万     18          9
 *       1百万     5           5
 *       10万      4          3
 *       1万       1          3
 */
public class ConcurrencyTest {

    /** 执行次数 */
    private static final Long count = 100000000l;

    public static void main(String[] args) throws InterruptedException {
        //并发计算
        concurrency();
        //单线程计算
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("并行:" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("串行:" + time + "ms,b=" + b + ",a=" + a);
    }

}
