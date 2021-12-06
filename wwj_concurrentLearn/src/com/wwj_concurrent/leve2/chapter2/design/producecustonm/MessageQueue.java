package com.wwj_concurrent.leve2.chapter2.design.producecustonm;

import java.util.LinkedList;

/**
 * @author zhang zhao lin
 * @date 2021年06月30日 23:40
 * @Description
 */
public class MessageQueue {
    private final LinkedList<Message> messageQueue;

    private final static int DEFAULT_MESSAGE_LIMIT = 100;

    private int limit;

    public MessageQueue() {
        this(DEFAULT_MESSAGE_LIMIT);
    }

    public MessageQueue(int limit) {
        this.limit = limit;
        messageQueue = new LinkedList<>();
    }

    public void put(final Message data) throws InterruptedException {
        synchronized (messageQueue) {
            while (messageQueue.size() > limit) {
                messageQueue.wait();
            }
            messageQueue.addLast(data);
            messageQueue.notifyAll();
        }
    }

    public synchronized  Message take() throws InterruptedException {
        synchronized (messageQueue) {
            while (messageQueue.isEmpty()) {
                messageQueue.wait();
            }
            Message message = messageQueue.removeFirst();
            messageQueue.notifyAll();
            return message;
        }
    }
}
