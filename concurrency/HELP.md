# ArtConcurrentBook: Java并发编程的艺术(方腾飞)
## chapter01
    ConcurrencyTest：多线程和串行速度测试
    DeadLockDemo：死锁演示、定位方法、避免方法
## chapter02
    Counter：使用循环CAS实现原子操作

## threadPool 线程池
线程池的创建分为两大类方法 
- 通过Executors自动创建 
- 通过ThreadPoolExecutor手动创建 

1. newFixedThreadPool：创建一个固定大小的线程池
2. newCachedThreadPool：带缓存的线程池，适用于短时间有大量任务的场景，但有可能会占用更多的资源；线程数量随任务量而定。
3. newSingleThreadExecuto:创建单个线程的线程池
4. newSingleThreadScheduledExecutor：创建执行定时任务的单个线程的线程池
5. newScheduledThreadPool：创建执行定时任务的线程池
6. newWorkStealingPool：根据当前设备的配置自动生成线程池
7. ThreadPoolExecutor：手动创建线程池
