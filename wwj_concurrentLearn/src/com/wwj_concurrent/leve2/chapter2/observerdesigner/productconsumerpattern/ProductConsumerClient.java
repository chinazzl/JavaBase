package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern
 * @Description:
 * @Date: 2021/7/7 10:25
 */
public class ProductConsumerClient {
    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue();
        new ProductThread(messageQueue,1).start();
        new ConsumerThread(messageQueue,1).start();

    }
}
