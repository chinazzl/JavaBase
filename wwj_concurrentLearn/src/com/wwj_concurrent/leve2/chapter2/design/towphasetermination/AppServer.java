package com.wwj_concurrent.leve2.chapter2.design.towphasetermination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhang zhao lin
 * @date 2021年07月13日 21:19
 * @Description
 */
public class AppServer extends Thread {

    private final int port;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private final List<ClientHandler> clientHandlers = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandlers.add(clientHandler);
                executorService.submit(clientHandler);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    public void dispose() {
        System.out.println("dispose");
        clientHandlers.forEach(ClientHandler::stop);
        this.executorService.shutdown();
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.server.close();
    }
}
