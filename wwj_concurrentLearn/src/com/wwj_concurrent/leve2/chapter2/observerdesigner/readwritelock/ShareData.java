package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock
 * @Description:
 * @Date: 2021/6/25 16:59
 */
public class ShareData {

    private char[] buffer;

    private final ReaderWriterLock lock = new ReaderWriterLock();

    public ShareData(int size) {
        buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public synchronized char[] read() {
        try {
            lock.readLock();
            return doread();
        } finally {
            lock.readUnlock();
        }
    }

    private synchronized char[] doread() {
        char[] newc = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newc[i] = buffer[i];
        }
        slowly(10);

        return newc;
    }

    public synchronized void write(char c) {
        try {
            lock.writeLock();
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
        }
        slowly(50);
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
