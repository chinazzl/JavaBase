package com.wwj_concurrent.leve1.chapter4;

import java.util.*;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/4 16:01
 * @Modified By：
 */
public class CaptureService {

    private final static LinkedList<Context> CONTROLS = new LinkedList<>();

    public static void main(String[] args) {
        List<Thread> works = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream()
                .map(CaptureService::createThreadService)
                .forEach(t -> {
                    works.add(t);
                    t.start();
                });
        works.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("The Capture works [ " + Thread.currentThread().getName() + "] were finished. ");
    }

    private static Thread createThreadService(String name) {
        return new Thread(() -> {
            synchronized (CONTROLS) {
                while (CONTROLS.size() > 5) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Context());
            }
            Optional.of("This works " + Thread.currentThread().getName() + " is working")
                    .ifPresent(System.out::println);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS) {
                Optional.of("The worker [" + Thread.currentThread().getName() + "] END capture data.")
                        .ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }

        }, name);

    }
}

class Context {

}
