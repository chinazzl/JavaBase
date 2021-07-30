package com.wwj_concurrent.leve1.chapter7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/28 9:29
 * @Modified By：
 * 1. 增加基础线程池功能
 * 2. 增加 线程池的拒绝策略和 关闭线程池的功能
 * 3. 增加 动态执行 扩容可释放线程池的大小
 */
public class SimpleThreadPool extends Thread {

    private int size;

    private final int queueSize;

    private final DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    private static final int DEFAULT_POOL_SIZE = 10;

    private static final int DEFAULT_THREAD_QUEUE_SIZE = 2000;

    // 用来存储 工作线程
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<Runnable>();

    private static final ThreadGroup THREAD_POOL_GROUP = new ThreadGroup("POOL_GROUP");

    private static final String THREAD_POOL_NAME_PREFIX = "TREAD_POOL-";

    //用来记录 线程池的集合
    private static final List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    private static volatile int seq = 0;

    // 线程池最小值
    private final int min;

    //线程池 可用数量
    private final int active;

    //线程池最大数量
    private final int max;


    private static final DiscardPolicy DISCARD_POLICY = () -> {
        throw new DiscardException("no enough thread Size to use.");
    };

    //default constructor
    public SimpleThreadPool() {
        this(2, 4, 12, DEFAULT_THREAD_QUEUE_SIZE, DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queue_size, DiscardPolicy discardPolicy) {
        this.queueSize = queue_size;
        this.discardPolicy = discardPolicy;
        this.min = min;
        this.active = active;
        this.max = max;
        init();
    }

    //初始化线程池
    private void init() {
        for (int i = 0; i < min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    //控制线程池 的扩容以及缩减
    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("The Pool #Min: %d, #Active: %d, #CurrentSize: %d, #Max: %d ,#QueueSize: %d\n",
                    this.min, this.active, this.size, this.max, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000);
                if (TASK_QUEUE.size() > size && size < active) {
                    System.out.println("The taskQueue has incremented to active.");
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    this.size = active;
                } else if (TASK_QUEUE.size() > size && size < max) {
                    System.out.println("The taskQueue has incremented to max. ");
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    this.size = max;
                }

                //一旦有空余 则进行 缩减线程池
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("========Reduce=======");
                        int released = size - active;
                        for (Iterator<WorkTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (released <= 0) break;
                            WorkTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            released--;
                        }
                        this.size = active;
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("The thread already shutDown not allowed");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    // 执行 关闭操作
    public void shutDown() {
        while (!TASK_QUEUE.isEmpty()) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (THREAD_QUEUE) {
            int intValue = TASK_QUEUE.size();
            while (intValue > 0) {
                for (WorkTask workTask : THREAD_QUEUE) {
                    WorkTaskStatus workStatus = workTask.workStatus;

                    if (workStatus == WorkTaskStatus.BLOCKED) {
                        workTask.interrupt();
                        workTask.close();
                        intValue--;
                    } else {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println(THREAD_POOL_GROUP.activeCount());
        this.destroy = true;
        System.out.println(" all is shutDown");


    }

    private void createWorkTask() {
        WorkTask workTask = new WorkTask(THREAD_POOL_GROUP, THREAD_POOL_NAME_PREFIX + (seq++));
        workTask.start();
        THREAD_QUEUE.add(workTask);
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    public static interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private enum WorkTaskStatus {
        FREE, RUNNABLE, BLOCKED, DEAD;
    }

    private class WorkTask extends Thread {
        private volatile WorkTaskStatus workStatus = WorkTaskStatus.FREE;

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public WorkTaskStatus getWorkStatus() {
            return workStatus;
        }

        Runnable runnable;

        @Override
        public void run() {
            OUTTER:
            while (this.workStatus != WorkTaskStatus.DEAD) {
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            workStatus = WorkTaskStatus.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    workStatus = WorkTaskStatus.RUNNABLE;
                    runnable.run();
                    workStatus = WorkTaskStatus.FREE;
                }
            }
        }

        public void close() {
            workStatus = WorkTaskStatus.DEAD;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        IntStream.rangeClosed(0, 40).forEach(i -> {
            simpleThreadPool.submit(() -> {
                System.out.println(" The thred pool begined , runing thread-" + i + " started.");
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" The thred pool begined , runing thread-" + i + " finished.");

            });
        });
        Thread.sleep(10000);
        simpleThreadPool.shutDown();
    }


}
