package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern
 * @Description: 生产者模型
 * @Date: 2021/7/7 9:51
 */
public class ProductThread extends  Thread {

    private final MessageQueue messageQueue;

    private final AtomicInteger counter = new AtomicInteger();

    private final static Random random = new Random(System.currentTimeMillis());

    private final int sequence;

    public ProductThread(MessageQueue messageQueue,int seq) {
        super("Product-" + seq);
        this.messageQueue = messageQueue;
        this.sequence = seq;
    }

    @Override
    public void run() {
        while (true) {
            final Message message = new Message("Messge-" + counter.incrementAndGet());
            try {
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " product send message + " + message.getMessage());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
