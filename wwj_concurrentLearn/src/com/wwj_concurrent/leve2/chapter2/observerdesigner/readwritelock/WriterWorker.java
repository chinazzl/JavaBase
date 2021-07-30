package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock;

import java.util.Random;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.readwritelock
 * @Description:
 * @Date: 2021/6/29 10:18
 */
public class WriterWorker extends Thread{
    private ShareData shareData;

    private String value;

    private int index;

    private final Random random = new Random(System.currentTimeMillis());

    public WriterWorker(ShareData shareData, String value) {
        this.shareData = shareData;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            while (true) {
                shareData.write(nextChar(value));
                Thread.sleep(random.nextInt(1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char nextChar(String value) {
        if (value == null) {
            return 0;
        }else {
            char c = value.charAt(index);
            index++;
            return c;
        }
    }
}
