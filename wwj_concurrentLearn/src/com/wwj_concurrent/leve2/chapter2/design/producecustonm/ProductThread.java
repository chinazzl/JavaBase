package com.wwj_concurrent.leve2.chapter2.design.producecustonm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhang zhao lin
 * @date 2021年07月06日 22:29
 * @Description
 */
public class ProductThread extends Thread {

    private final MessageQueue messageQueue;

    private AtomicInteger counter = new AtomicInteger();

    public ProductThread(MessageQueue messageQueue, int seq) {
        super("Product-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            Message message = new Message("Message " + counter.incrementAndGet());
            try {
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + "Product MessageQueue send message => " + message.getData());
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
