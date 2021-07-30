package com.wwj_concurrent.leve2.chapter2.observerdesigner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner
 * @Description:
 * @Date: 2021/6/17 16:24
 */
public class Subject {

    private int state;

    private List<ObServer> observers = new ArrayList<>();

    public void attach(ObServer obServer) {
        observers.add(obServer);
    }

    public void setState(int state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        notifySubject();
    }

    private void notifySubject() {
        observers.forEach(ObServer::update);
    }

    public int getState() {
        return state;
    }


}
