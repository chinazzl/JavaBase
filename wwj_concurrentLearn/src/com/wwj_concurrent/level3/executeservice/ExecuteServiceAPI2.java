package com.wwj_concurrent.level3.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月27日 22:33
 * @Description: learn ExecutorService some Api
 * 1. coreSize：核心线程创建时机
 * 2. allowTimeout：允许核心线程空闲的时候进行回收
 * 3. preStartCoreThread：预启动核心线程
 * 4. ThreadPoolAdvice：执行thread会执行前置和后置方法
 * 5. remove：将线程从队列中进行移除
 **********************************/
public class ExecuteServiceAPI2 {
    public static void main(String[] args) throws InterruptedException {
        testCoreThread();
    }

    private static void testCoreThread() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 创建之后执行初始化操作 设置核心线程为2，当提交任务会放到queue中，创建一个线程
        System.out.println(executorService.getActiveCount());
        // 提交一个任务 对照core中的数量 创建一个线程
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(1);
        // 之创建一个
        System.out.println(executorService.getActiveCount());
    }
}
