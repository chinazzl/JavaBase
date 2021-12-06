package com.wwj_concurrent.leve2.chapter2.design.singletonthread;

/**
 * @author zhang zhao lin
 * @date 2021年06月21日 21:39
 * @Description
 */
public class Gate {

    private String name = "Nowhere";

    private String person = "Nobody";

    private int count = 0;

    public synchronized void pass(String name, String person) {
        this.name = name;
        this.person = person;
        this.count++;
        verify(name, person);
    }

    private void verify(String name, String person) {
        if (name.charAt(0) != person.charAt(0)) {
            System.out.println("****Broken**** " + toString());
        }
    }

    public synchronized String toString() {
        return "No." + count + " ： " + name + ", " + person;
    }

}
