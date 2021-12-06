package com.wwj_concurrent.leve2.chapter2.design.guardedsuspensionpattern;

import java.util.LinkedList;

/**
 * @author zhang zhao lin
 * @date 2021年06月24日 23:26
 * @Description
 */
public class RequestQuee {

    private final LinkedList<Request> queue = new LinkedList<Request>();

    public Request getQueue() {
        synchronized (queue) {
            try {
                while (queue.size() <= 0) {
                    queue.wait();
                    return null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return queue.getFirst();
        }
    }


    public void putQueue(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
