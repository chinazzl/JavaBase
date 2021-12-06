package com.wwj_concurrent.leve2.chapter2.design.guardedsuspensionpattern;

import java.util.Random;

/**
 * @author zhang zhao lin
 * @date 2021年06月27日 23:08
 * @Description
 */
public class RequestClient extends Thread{
    private final Random random;

    private final String sendValue;

    private final RequestQuee requestQuee;

    public RequestClient(String sendValue, RequestQuee requestQuee) {
        this.random = new Random(System.currentTimeMillis());
        this.sendValue = sendValue;
        this.requestQuee = requestQuee;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            requestQuee.putQueue(new Request(sendValue));
            System.out.println(" The Request Client running -> send value is : " + sendValue);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
