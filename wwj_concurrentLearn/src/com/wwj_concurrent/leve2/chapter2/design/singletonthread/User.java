package com.wwj_concurrent.leve2.chapter2.design.singletonthread;

/**
 * @author zhang zhao lin
 * @date 2021年06月21日 21:55
 * @Description
 */
public class User extends Thread {
    private final String name;

    private final String person;

    private Gate gate;

    public User(String name, String person,Gate gate) {
        this.name = name;
        this.person = person;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(name + " Begin");
        while (true) {
            gate.pass(name,person);
        }
    }
}
