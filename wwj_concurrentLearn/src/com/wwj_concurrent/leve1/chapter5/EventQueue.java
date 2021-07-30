package com.wwj_concurrent.leve1.chapter5;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/8/27 14:20
 * @Modified By：
 *    单线程之间的通信
 */
public class EventQueue {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue(int max) {
        this.max = max;
    }

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= DEFAULT_MAX_EVENT) {
                try {
                    console("this list already full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                eventQueue.addLast(event);
                eventQueue.notify();
                console("this queue is submitted");
            }
            System.out.println("1");
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            if(eventQueue.isEmpty()) {
                try {
                    console("this queue is Empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            eventQueue.notify();
            console("this event " + event + "is handled.");
            return event;
        }
    }

    private void console(String message) {
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }
}
