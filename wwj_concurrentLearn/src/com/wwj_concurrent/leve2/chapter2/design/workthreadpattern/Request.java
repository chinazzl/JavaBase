package com.wwj_concurrent.leve2.chapter2.design.workthreadpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月14日 21:46
 * @Description
 **********************************/
public class Request {
    private String name;

    private int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString() {
        return "Request=> No. " + number + " Name. " + name;
    }
}
