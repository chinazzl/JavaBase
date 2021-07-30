package com.wwj_concurrent.leve1.chapter3;

import java.util.concurrent.TimeUnit;


public class Mutext {

    private final static Object MUTEX = new Object();


    public static void main(String[] args) {
        final Mutext mutext = new Mutext();
        for (int i = 0; i < 5; i++) {
            new Thread(mutext::accessResource).start();
        }
    }
    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    

}
