package com.wwj_concurrent.level3.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2021年12月26日 15:11
 * @Description: learn ExecuteService
 **********************************/
public class ExecuteServiceAPI {
    public static void main(String[] args) throws InterruptedException {
//        isShutDown();
//        isTerminated();
        ThreadErrors();
    }

    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        // 尝试进行关闭，但是该方法并不阻塞
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
    }

    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        // 尝试进行关闭，但是该方法并不阻塞
        executorService.shutdown();
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
    }

    private static void ThreadErrors() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(i / 0)));
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(new MyRunnableTask(i) {
            @Override
            protected void doSuccess() {
                System.out.println("Thread-" + i + " execute SUCCESS. will be DONE");
            }

            @Override
            protected void doExecute() {
                int tmp = 1/0;
            }

            @Override
            protected void doError(Throwable e) {
                System.out.println(Thread.currentThread().getName() + " execute Failed.");
            }

            @Override
            protected void doInit() {

            }
        }));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

    protected abstract static class MyRunnableTask implements Runnable {
        private int no;

        public MyRunnableTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.doSuccess();
            } catch (Throwable e) {
                this.doError(e);
            }
        }

        protected abstract void doSuccess();

        protected abstract void doExecute();

        protected abstract void doError(Throwable e);

        protected abstract void doInit();
    }

    /**
     * 使用Thread捕获异常，但是无法捕获是哪个线程出现问题，并且无法分别进行异常特殊处理
     */
    static class MyThreadFactory implements ThreadFactory {

        private AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            System.out.println("MY-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("Thread -" + t.getName() + " execute failed.");
                cause.printStackTrace();
                System.out.println("===============================");
            });
            return thread;
        }
    }

}
