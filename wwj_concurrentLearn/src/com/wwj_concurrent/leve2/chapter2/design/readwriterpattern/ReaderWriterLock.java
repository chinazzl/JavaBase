package com.wwj_concurrent.leve2.chapter2.design.readwriterpattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月22日 20:46
 * @Description
 */
public class ReaderWriterLock {

    private int readingReader = 0;

    private int waitingReader = 0;

    private int writingWriter = 0;

    private int waitingWriter = 0;

    public synchronized void readerLock() {
        waitingReader++;
        try {
            while (writingWriter > 0) {
                this.wait();
            }
            readingReader++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            waitingReader--;
        }
    }

    public synchronized void readerUnlock() {
        readingReader--;
        this.notifyAll();
    }

    public synchronized void writerLock() {
        waitingWriter++;
        try {
            while (readingReader > 0 || writingWriter >0) {
                this.wait();
            }
            writingWriter++;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            waitingWriter--;
        }
    }

    public synchronized void writerUnlock() {
        writingWriter--;
        this.notifyAll();
    }

}
