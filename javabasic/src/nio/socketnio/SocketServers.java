package nio.socketnio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.socketnio
 * @Description: socket 服务端
 * @Date: 2021/11/4 10:27
 */
public class SocketServers {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9002);
            System.out.println(" 服务器创建响应之前开始时间 " + System.currentTimeMillis());
            Socket socket = server.accept();
            System.out.println(" 阻塞后接收数据时间 " + System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
