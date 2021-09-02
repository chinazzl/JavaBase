package ioLearn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package ioLearn.bio
 * @Description:
 * @Date: 2021/8/13 16:18
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3333);
        new Thread(() -> {
            while (true) {
                try {
                    //阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    //每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        int len;
                        byte[] data = new byte[1024];
                        try {
                            InputStream inputStream = socket.getInputStream();
                            //按照字节流 方式读取
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data,0,len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
