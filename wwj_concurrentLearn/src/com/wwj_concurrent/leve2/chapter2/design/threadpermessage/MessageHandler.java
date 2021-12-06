package com.wwj_concurrent.leve2.chapter2.design.threadpermessage;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhang zhao lin
 * @date 2021年07月08日 22:27
 * @Description
 */
public class MessageHandler {

    private final static Random random = new Random(System.currentTimeMillis());

    private final Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            try {
               final String value = message.getValue();
                Thread.sleep(random.nextInt(1000));
                System.out.println("The Message will be handler. The Name is  " + Thread.currentThread().getName() + " ->" + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutDown() {
        ((ExecutorService)executor).shutdown();
    }
}
