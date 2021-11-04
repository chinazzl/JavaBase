package nio.socketnio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.socketnio
 * @Description:
 * @Date: 2021/11/4 10:48
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            System.out.println("创建连接开始 " + System.currentTimeMillis());
            Socket socket = new Socket("www.baidu.com", 80);
            System.out.println("连接结束 " + System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端 连接失败");
        }
    }
}
