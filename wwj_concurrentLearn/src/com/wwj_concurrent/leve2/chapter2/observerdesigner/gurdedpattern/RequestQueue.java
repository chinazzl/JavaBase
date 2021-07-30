package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern;

import java.util.LinkedList;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern
 * @Description:
 * @Date: 2021/6/29 15:04
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getQueue() {
        synchronized (queue) {
            try {
                while (queue.size() <= 0) {
                    queue.wait();
                }
            } catch (Exception e) {
                return null;
            }
            return queue.removeFirst();
        }
    }

    public void putQueue(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
