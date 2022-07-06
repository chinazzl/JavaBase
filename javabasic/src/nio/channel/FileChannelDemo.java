package nio.channel;

import langer.Ex;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.channel
 * @Description:
 * @Date: 2022/7/5 21:23
 */
public class FileChannelDemo {

    private static String logPath;
    private static String targetPath;

    static {
        logPath = Optional.ofNullable(FileChannelDemo.class.getClassLoader().getResource("mylog.log")).map(URL::getPath).orElse("");
        targetPath = Optional.ofNullable(FileChannelDemo.class.getClassLoader().getResource("new.md")).map(URL::getPath).orElse("");
    }

    public static void main(String[] args) {
        baseReader();
//        fileWrite();
//        fileSyncWriter();
    }

    /**
     * IO基础
     */
    private static void baseReader() {
        try (InputStream inputStream = Files.newInputStream(new File(logPath).toPath());
            OutputStream outputStream = Files.newOutputStream(new File(targetPath).toPath())) {
            int len = -1;
            byte[] bytes = new byte[1024];
            String out = null;
            while ((len = inputStream.read(bytes)) > 0) {
                out += new String(bytes,0,len);
                outputStream.write(bytes,0,len);
            }
            System.out.println(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * int write(ByteBuffer src) 方法是从通道的当前位置开始写入
     */
    private static void fileWrite() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(logPath);
             FileChannel channel = fileOutputStream.getChannel();) {
            ByteBuffer byteBuffer = ByteBuffer.wrap("abcd".getBytes());
            System.out.println("A fileChannel position =>" + channel.position());
            System.out.println("write() 1 返回值：" + channel.write(byteBuffer));
            System.out.println("B fileChannel position =>" + channel.position());
            channel.position(2);
            byteBuffer.rewind();
            System.out.println("write() 2 返回值：" + channel.write(byteBuffer));
            System.out.println("C file channel position => " + channel.position());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证int write(ByteBuffer src) 具有同步特性
     * 总会出现：
     * abcde
     * 你好外星人
     * 不会出现：
     * a你好b外星de人
     * 说明write是同步的
     */
    private static void fileSyncWriter() {
        try (FileOutputStream fosRef = new FileOutputStream(logPath);
            FileChannel fileChannel = fosRef.getChannel();) {
            for (int i = 0; i < 10; i++) {
                Thread t1 = new Thread(() -> {
                    ByteBuffer buffer = ByteBuffer.wrap("abcde\n".getBytes(StandardCharsets.UTF_8));
                    try {
                        fileChannel.write(buffer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                Thread t2 = new Thread(() -> {
                    ByteBuffer buffer = ByteBuffer.wrap("你好外星人\n".getBytes(StandardCharsets.UTF_8));
                    try {
                        fileChannel.write(buffer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                t1.start();
                t2.start();
            }
            System.out.println("== do somethings ====");
            sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
