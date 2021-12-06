package com.wwj_concurrent.leve2.chapter2.design.readwriterpattern;

import java.util.concurrent.TimeUnit;

/**
 * @author zhang zhao lin
 * @date 2021年06月22日 21:50
 * @Description
 */
public class ShareData {
    //需要共享的数据
    private char[] buffer;
    private final ReaderWriterLock lock = new ReaderWriterLock();

    /**
     * 构建buffer 数据
     *
     * @param size
     * @return null
     * @date 2021/6/22 22:11
     */
    public ShareData(int size) {
        buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() {
        try {
            lock.readerUnlock();
            return doReader();
        } finally {
            lock.readerUnlock();
        }

    }

    private char[] doReader() {
        char[] newbuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuffer[i] = buffer[i];
            slowly(50);
        }
        return newbuffer;
    }

    private void slowly(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(char c) {
        try {
            lock.writerLock();
            doWrite(c);
        }finally {
            lock.writerUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(50);
        }
    }

}
