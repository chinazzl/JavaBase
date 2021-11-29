package com.wwj_concurrent.level3.lock.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock
 * @Description: 读写分离锁
 * @Date: 2021/11/28 13:46
 */
public class ReadAndWriteLock {

    //    private static final Lock lock = new ReentrantLock(true);
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock readLock = lock.readLock();
    private static final Lock writeLock = lock.writeLock();
    private static final List<Long> data = new ArrayList<>();

    public static void main(String[] args) {
        Thread writeThread = new Thread(ReadAndWriteLock::writeLock);
        writeThread.start();
        Thread readThread = new Thread(ReadAndWriteLock::writeLock);
        readThread.start();

    }

    private static void writeLock() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
        } finally {
            writeLock.unlock();
        }
    }

    private static void readLock() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            System.out.println("=================");
        } finally {
            readLock.unlock();
        }
    }
}
