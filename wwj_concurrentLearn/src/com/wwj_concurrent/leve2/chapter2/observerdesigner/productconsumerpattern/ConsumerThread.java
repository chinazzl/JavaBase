package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern
 * @Description:  消费者模型
 * @Date: 2021/7/7 10:20
 */
public class ConsumerThread extends Thread{
    private final MessageQueue messageQueue;

    private final AtomicInteger counter = new AtomicInteger();

    private final static Random random = new Random(System.currentTimeMillis());

    private final int sequence;

    public ConsumerThread(MessageQueue messageQueue,int seq) {
        super("Consumer-" + seq);
        this.messageQueue = messageQueue;
        this.sequence = seq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " product send message -> " + message.getMessage());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
