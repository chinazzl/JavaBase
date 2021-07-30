package org.javabase.reflect;

public class locKDemo {

    volatile int i = 0;

    private void add() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        locKDemo ld = new locKDemo();
        for (int j = 0; j < 2; j++) {
            new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);
    }
}
