package com.wwj_concurrent.level3.executeservice;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Julyan
 * @version V1.0
 * @Description: ScheduledThreadPoolExecutor有ThreadPoolExecutor的作用还支持ScheduledExecutorService特性
 * @Date: 2022/1/7 14:46
 */
public class ScheduledExecutorServiceSimple {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testSchedule();
        testScheduleExecutorDelay();
    }

    private static void testSchedule() throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        System.out.println(scheduledExecutorService.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        // runnable
        scheduledExecutorService.schedule(() -> System.out.println("After 2 seconds print this. "), 2, TimeUnit.SECONDS);
        // callable
        ScheduledFuture<Integer> scheduledFuture = scheduledExecutorService.schedule(() -> 10, 2, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.get());
    }

    private static void testScheduleAtFixedRate() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);

        /*
            当任务处理时间大于定时任务规定时间，依然会等上一个任务执行后再执行，延续Timer类
         */
        AtomicLong atomicLong = new AtomicLong(0);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            long interval = currentTimeMillis - atomicLong.get();
            if (atomicLong.get() == 0) {
                System.out.println("trigger first running at " + currentTimeMillis);
                atomicLong.set(currentTimeMillis);
            } else {
                System.out.println("delay 1 sec running. rate 2 sec. spend times [" + interval + "]");
            }
            try {
                System.out.println(Thread.currentThread().getName());
//                TimeUnit.SECONDS.sleep(5);
                TimeUnit.SECONDS.sleep(random.nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicLong.set(currentTimeMillis);
        }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * 指定一个延迟时间 真实的时间为 任务处理时间 + 设置delay时间
     */
    private static void testScheduleExecutorDelay() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        AtomicLong atomicLong = new AtomicLong(0);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            long interval = currentTimeMillis - atomicLong.get();
            if (atomicLong.get() == 0) {
                System.out.println("trigger first running at " + currentTimeMillis);
                atomicLong.set(currentTimeMillis);
            } else {
                System.out.println("delay 1 sec running. rate 2 sec. spend times [" + interval + "]");
            }
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicLong.set(currentTimeMillis);
        }, 1, 2, TimeUnit.SECONDS);
    }
}
