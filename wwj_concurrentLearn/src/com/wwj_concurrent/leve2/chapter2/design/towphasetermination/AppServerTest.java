package com.wwj_concurrent.leve2.chapter2.design.towphasetermination;

import java.io.IOException;

/**
 * @author zhang zhao lin
 * @date 2021年07月13日 22:21
 * @Description
 */
public class AppServerTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer server = new AppServer(13345);
        server.start();
        Thread.sleep(35_000);
        server.shutdown();
    }
}
