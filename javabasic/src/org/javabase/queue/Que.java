package org.javabase.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Java Querue队列
 */
public class Que {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        queue.add("2");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
