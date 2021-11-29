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
 * @Description:
 * @Date: 2021/11/29 18:24
 */
public class StampLockExample {

    private final static StampedLock stampedLock = new StampedLock();
    private static final LinkedList<Long> datas = new LinkedList<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Runnable readRunnable = () -> {
            for (; ; ) {
                read();
            }
        };

        Runnable writeRunnable = () -> {
            for (; ; ) {
                write();
            }
        };
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

    private static void read() {
        long stamp = -1;
        try {
            stamp = stampedLock.readLock();
            Optional.of(datas.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ";")))
                    .ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    private static void write() {
        long stamp = -1;
        try {
            stamp = stampedLock.writeLock();
            datas.addLast(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

}
