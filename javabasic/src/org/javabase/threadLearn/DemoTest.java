package org.javabase.threadLearn;

/**
 * Created by Felix on 2017/7/31.
 */
public class DemoTest {
    public static void main(String[] args) {
        ExtendThread thread1 = new ExtendThread("A");
        ExtendThread thread2 = new ExtendThread("B");
        thread1.start();
        thread2.start();
    }
}
