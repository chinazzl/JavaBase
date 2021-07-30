package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock
 * @Description:
 * @Date: 2021/6/25 17:10
 */
public class ReadWorker extends Thread{

    private ShareData shareData;

    public ReadWorker(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] read = shareData.read();
                System.out.println("Thread read char :" + String.valueOf(read));
            }
        } catch (Exception e) {

        }
    }
}
