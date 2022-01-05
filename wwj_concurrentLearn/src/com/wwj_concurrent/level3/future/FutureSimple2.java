package com.wwj_concurrent.level3.future;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/1/4 16:15
 */
public class FutureSimple2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testIsDone();
        testCancel();
    }

    /**
     * isDone is true :
     * 1. 正常执行成功
     * 2. 抛出异常
     * 3. 被取消 执行cancel
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testIsDone() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
           /* try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;*/
            throw new RuntimeException("执行出现异常也会调用isDone成功。");
        });
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            //  is Done >true
            System.out.println(" is Done >" + future.isDone());
        }
    }

    /**
     * Attempts to cancel execution of this task.
     * <p>
     * 1. This attempt will fail if the task has already completed,
     * <p>
     * 2. has already been cancelled,
     * <p>
     * 3. could not be cancelled for some other reason.
     * <p>
     * If successful,and this task has not started when {@code cancel} is called,
     * this task should never run.  If the task has already started,
     * then the {@code mayInterruptIfRunning} parameter determines
     * whether the thread executing this task should be interrupted in
     * an attempt to stop the task.
     * <p>
     * param mayInterruptIfRunning {@code true} if the thread executing this
     * task should be interrupted; otherwise, in-progress tasks are allowed
     * to complete
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(
                // 对于很大很大的任务想要强制退出，则需要重写ThreadFactory并且设置守护线程
                new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
           /* try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            // 对于很大很大的任务想要强制退出，则需要重写ThreadFactory并且设置守护线程
            while (running.get()) {

            }
            // 如果有 很耗时的任务进行取消的话，使用Thread和 interupt结合使用，一般做法
          /*  while (!Thread.interrupted()) {

            }*/
            System.out.println("11111111");
            return 10;
        });
        // 如果任务已经完成 则cancel失败
//        Integer result = future.get();
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        // false
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
    }
}
