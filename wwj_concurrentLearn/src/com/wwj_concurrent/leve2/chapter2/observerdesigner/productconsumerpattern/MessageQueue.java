package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern;

import java.util.LinkedList;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern
 * @Description:
 * @Date: 2021/7/7 9:36
 */
public class MessageQueue {

    private static final int DEFAULT_MAX_LIMIT = 100;

    private final LinkedList<Message> queue;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit) {
        queue = new LinkedList<>();
        this.limit = limit;
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }


}
