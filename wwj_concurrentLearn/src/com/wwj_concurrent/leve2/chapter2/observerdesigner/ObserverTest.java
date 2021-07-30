package com.wwj_concurrent.leve2.chapter2.observerdesigner;


/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner
 * @Description:
 * @Date: 2021/6/18 10:02
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        subject.setState(10);

    }
}
