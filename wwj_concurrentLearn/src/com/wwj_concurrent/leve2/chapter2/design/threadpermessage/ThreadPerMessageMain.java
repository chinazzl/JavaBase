package com.wwj_concurrent.leve2.chapter2.design.threadpermessage;

import java.util.stream.IntStream;

/**
 * @author zhang zhao lin
 * @date 2021年07月08日 22:35
 * @Description
 */
public class ThreadPerMessageMain {
    public static void main(String[] args) {
        final MessageHandler messageHandler = new MessageHandler();
        IntStream.rangeClosed(0,10).forEach(i -> {
            messageHandler.request(new Message(String.valueOf(i)));
        });
        messageHandler.shutDown();
    }
}
