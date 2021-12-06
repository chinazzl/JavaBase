package com.wwj_concurrent.leve2.chapter2.design.readwriterpattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月22日 22:38
 * @Description
 */
public class ReaderWorker extends Thread{
    private ShareData shareData;

    public ReaderWorker(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        while (true) {
            char[] chars = shareData.read();
            System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(chars));
        }
    }
}
