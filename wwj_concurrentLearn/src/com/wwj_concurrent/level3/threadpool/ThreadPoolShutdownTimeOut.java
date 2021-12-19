package com.wwj_concurrent.level3.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月18日 17:30
 * @Description: 对于网络请求资源长时间未响应，直接进行结束
 **********************************/
public class ThreadPoolShutdownTimeOut {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            // 主线程结束了但是，工作线程无法结束导致程序一直卡住，线程池无法进行结束
            // 解决方法：将threadFactory设置守护线程
            t.setDaemon(true);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0,10).boxed().forEach(i -> {
            threadPoolExecutor.execute(() -> {
                // 模拟长时间未得到想要结果导致程序卡住
                while (true) {

                }
            });
        });
        /*
            对当前线程池在执行一段时间后进行结束
                1. 10个线程进行工作
                2. 将10个idle的 线程进行interupt
                3. 将20个线程继续宁exit。
            主线程结束了但是，工作线程无法结束导致程序一直卡住，线程池无法进行结束
         */
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(5,TimeUnit.SECONDS);
        System.out.println("======================OVer========================");
    }
}
