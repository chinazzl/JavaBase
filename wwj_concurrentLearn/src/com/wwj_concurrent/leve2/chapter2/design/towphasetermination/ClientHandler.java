package com.wwj_concurrent.leve2.chapter2.design.towphasetermination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author zhang zhao lin
 * @date 2021年07月13日 21:36
 * @Description
 */
public class ClientHandler implements Runnable {
    private Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter writer = new PrintWriter(outputStream);) {
            while (running) {
                String message = br.readLine();
                if (message == null) break;
                System.out.println("coming from message => " + message);
                writer.write("echo " + message + "\n");
                writer.flush();
            }

        } catch (IOException e) {
            this.running = false;
        }finally {
            this.stop();
        }
    }

    public void stop() {
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            //...
        }
    }
}
