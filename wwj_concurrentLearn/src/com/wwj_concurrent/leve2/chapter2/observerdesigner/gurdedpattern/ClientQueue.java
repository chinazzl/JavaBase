package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern;

import java.util.Random;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern
 * @Description:
 * @Date: 2021/6/29 15:14
 */
public class ClientQueue extends Thread {

    private final RequestQueue requestQueue;

    private String sendValue;

    private final Random random;

    public ClientQueue(String sendValue, RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        this.sendValue = sendValue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Client Queue put value -> " + sendValue);
            requestQueue.putQueue(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
            }
        }
    }
}
