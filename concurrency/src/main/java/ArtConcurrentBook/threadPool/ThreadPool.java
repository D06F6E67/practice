package ArtConcurrentBook.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 手动创建线程池
 *
 * @author Lee
 */
public class ThreadPool {
    public static void main(String[] args) {
        //线程工厂
        ThreadFactory factory = r -> {
            Thread thread = new Thread(r);

            return thread;
        };
        //手动创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                // 核心（最少）线程数
                2,
                // 最大线程数
                4,
                // 闲置可存活时间
                3,
                // 描述（闲置可存活时间）的单位
                TimeUnit.SECONDS,
                // 任务队列
                new LinkedBlockingDeque<>(2),
                // 线程工厂
                factory,
                /*
                  拒绝策略有5种：
                    //1.提示异常，拒绝执行多余的任务
                    // new ThreadPoolExecutor.AbortPolicy()

                    //2.忽略堵塞队列中最旧的任务
                    //new ThreadPoolExecutor.DiscardOldestPolicy()

                    //3.忽略最新的任务
                    //new ThreadPoolExecutor.DiscardPolicy()

                    //4.使用调用该线程池的线程来执行任务
                    //new ThreadPoolExecutor.CallerRunsPolicy()

                    //5.自定义拒绝策略
                    new RejectedExecutionHandler()
                 */
                //自定义拒绝策略
                (r, executor) -> System.out.println("拒绝策略")
        );
        //任务
        // for (int i = 0; i < Integer.SIZE; i++) {
        //     int finalI = i;
        //     threadPoolExecutor.submit(() -> {
        //         try {
        //             Thread.sleep(finalI * 100);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //         System.out.println(Thread.currentThread().getName() + "任务名" + finalI);
        //     });
        // }

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Future<Integer> submit = threadPoolExecutor.submit(() -> add(finalI));
            futures.add(submit);
        }

        for (int i = 0; i < futures.size(); i++) {
            try {
                System.out.println(futures.get(i).get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static Integer add(Integer i) {
        return i;
    }
}
