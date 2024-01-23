package nio.socketnio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**********************************
 * @author zhang zhao lin
 * @date 2023年05月18日 23:13
 * @Description:
 **********************************/
public class CreateWebServer {

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader readerReader = new BufferedReader(reader);
            String getStr = "";
            while (!"".equals((getStr = readerReader.readLine()))) {
                System.out.println(getStr);

            }
            serverSocket.close();
            inputStream.close();
            readerReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
