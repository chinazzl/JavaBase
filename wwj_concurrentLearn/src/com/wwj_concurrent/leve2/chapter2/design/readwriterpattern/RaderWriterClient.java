package com.wwj_concurrent.leve2.chapter2.design.readwriterpattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月22日 23:31
 * @Description
 */
public class RaderWriterClient {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData(10);
        ReaderWorker r1 = new ReaderWorker(shareData);
        ReaderWorker r2 = new ReaderWorker(shareData);
        ReaderWorker r3 = new ReaderWorker(shareData);
        ReaderWorker r4 = new ReaderWorker(shareData);

        WriteWork w1 = new WriteWork(shareData,"w1234");
        WriteWork w2 = new WriteWork(shareData,"q1234");
        WriteWork w3 = new WriteWork(shareData,"e1234");

        r1.start();
        r2.start();
        r3.start();
        r4.start();
        w1.start();
        w2.start();
        w3.start();

    }
}
