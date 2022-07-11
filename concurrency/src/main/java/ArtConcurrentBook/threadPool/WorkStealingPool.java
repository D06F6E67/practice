package ArtConcurrentBook.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 根据当前设备的配置自动生成线程池
 * @author Lee
 */
public class WorkStealingPool {
    public static void main(String[] args) {
        ExecutorService service= Executors.newWorkStealingPool();
        for(int i=0;i<50;i++){
            int finalI = i;
            service.submit(()->{
                System.out.println(finalI +"线程名"+Thread.currentThread().getName());//线程名有多少个，CPU就创建了多少个线程
            });
        }
        //创建的为守护线程，JVM不会等待守护线程结束
        while (!service.isTerminated()){

        }
    }
}
