package com.wwj_concurrent.leve1.chapter2;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/13 15:46
 * @Modified By：
 * join某个线程A，会使当前线程B 进入等待，直到线程A结束生命周期，或者到达给定的时间，那么在此期间B 线程是
 * 处于Blocked的，而不是A线程，
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        // 定义两个线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(ThreadJoin::creaate).collect(Collectors.toList());
        threads.forEach(Thread::start);
        // 执行这两个线程的join
        for (Thread thread : threads) {
            thread.join();
        }

        //main循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    //构建一个简单的线程，每个线程进行循环输出
    public static Thread creaate(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "##" + i);
            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
