package com.wwj_concurrent.level3.lock.stamplock;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock.stamplock
 * @Description: 乐观锁
 * @Date: 2021/11/30 16:38
 */
public class StampedOptimisticExample {
    private static final StampedLock lock = new StampedLock();
    private static final LinkedList<Long> data = new LinkedList<>();

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable writeRunnable = StampedOptimisticExample::beginWrite;
        Runnable readRunnable = StampedOptimisticExample::beginRead;

        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(writeRunnable);


    }

    private static void beginRead() {
        for (; ; ) {
            read();
        }
    }

    private static void beginWrite() {
        for (; ; ) {
            write();
        }
    }

    private static void read() {
        // 不阻塞乐观锁
        long stamp = lock.tryOptimisticRead();
        // 如果没有人对这个锁进行修改 返回true ，有人修改返回false
        if (!lock.validate(stamp)) {
            try {
                stamp = lock.readLock();
                Optional.of(data.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", "")))
                        .ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }

    private static void write() {
        long stamp = -1;
        try {
            stamp = lock.writeLock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
