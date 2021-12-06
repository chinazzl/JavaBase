package com.wwj_concurrent.leve2.chapter2.design.guardedsuspensionpattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月27日 23:29
 * @Description
 */
public class GuardSuspendsionTest {

    public static void main(String[] args) throws InterruptedException {
        final RequestQuee requestQuee = new RequestQuee();
        RequestClient requestClient = new RequestClient("Tom",requestQuee);
        requestClient.start();

        RequestServer requestServer = new RequestServer(requestQuee);
        requestServer.start();

        Thread.sleep(10000);
        requestServer.close();
    }

}
