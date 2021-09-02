package ioLearn.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package ioLearn.bio
 * @Description:
 * @Date: 2021/8/13 16:13
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("129.1.1.1", 3333);
                while (true) {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    Thread.sleep(2_000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
