package com.wwj_concurrent.leve2.chapter2.design.readwriterpattern;

import java.util.Random;

/**
 * @author zhang zhao lin
 * @date 2021年06月22日 23:16
 * @Description
 */
public class WriteWork extends Thread {
    private ShareData shareData;
    private final Random random = new Random(System.currentTimeMillis());

    private int index;

    private String filler;

    public WriteWork(ShareData shareData, String filler) {
        this.shareData = shareData;
        this.filler = filler;
    }

    @Override
    public void run() {
        while (true) {
            char ch = nexChar(filler);
            System.out.println(Thread.currentThread().getName() + " write " + String.valueOf(ch));
            shareData.write(ch);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private char nexChar(String filler) {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }


}
