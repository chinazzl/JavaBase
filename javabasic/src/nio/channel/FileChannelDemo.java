package nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        fileBathReadSync();
        //fileBatchReader();
//        fileBatchWriter();
//        fileReader2();
//        baseReader();
//        fileReader();
//        fileWrite();
//        fileSyncWriter();
    }

    private static void fileBathReadSync() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(logPath));
             FileChannel fileChannel = fileInputStream.getChannel();) {
            for (int i = 0; i < 10; i++) {
                Thread t1 = new Thread(() -> {
                    ByteBuffer b1 = ByteBuffer.allocate(8);
                    ByteBuffer b2 = ByteBuffer.allocate(7);
                    ByteBuffer[] bs = {b1, b2};
                    System.out.println(Thread.currentThread().getName() + " in");
                    try {
                        long readLength = fileChannel.read(bs);
                        while (readLength != -1) {
                            synchronized (FileChannelDemo.class) {
                                for (ByteBuffer b : bs) {
                                    byte[] array = b.array();
                                    for (byte bt : array) {
                                        System.out.print((char) bt);
                                    }
                                }
                            }
                            b1.clear();
                            b2.clear();
                            readLength = fileChannel.read(bs);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
                Thread t2 = new Thread(() -> {
                    ByteBuffer b1 = ByteBuffer.allocate(10);
                    ByteBuffer b2 = ByteBuffer.allocate(10);
                    ByteBuffer[] bs = {b1, b2};
                    try {
                        long readLength = fileChannel.read(bs);
                        while (readLength != -1) {
                            synchronized (FileChannelDemo.class) {
                                for (ByteBuffer b : bs) {
                                    byte[] array = b.array();
                                    for (byte bt : array) {
                                        System.out.print((char) bt);
                                    }
                                }
                            }
                            b1.clear();
                            b2.clear();
                            readLength = fileChannel.read(bs);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                t1.start();
                t2.start();
                sleep(30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量读操作
     */
    private static void fileBatchReader() {
        long readLength = 0;
        try (FileInputStream fileInputStream = new FileInputStream(new File(logPath)); FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(2);
            ByteBuffer b2 = ByteBuffer.allocate(2);
            ByteBuffer[] bs = new ByteBuffer[]{b1, b2};
            readLength = fileChannel.read(bs);
            /*
            System.out.println(readLength); // 4
            b1.clear();
            b2.clear();
            readLength = fileChannel.read(bs);
            System.out.println(readLength); // 3
            b1.clear();
            b2.clear();
            readLength = fileChannel.read(bs);
            System.out.println(readLength); //  -1
            b1.clear();
            b2.clear();*/
            for (ByteBuffer byteBuffer : bs) {
                byte[] bytes = byteBuffer.array();
                for (byte b : bytes) {
                    System.out.print((char) b + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * write 批量操作
     */
    private static void fileBatchWriter() {
        try (FileOutputStream fileInputStream = new FileOutputStream(new File(logPath)); FileChannel fileChannel = fileInputStream.getChannel()) {
            fileChannel.write(ByteBuffer.wrap("123456".getBytes()));
            fileChannel.position(3);
            ByteBuffer byteBuffer1 = ByteBuffer.wrap("00101".getBytes());
            ByteBuffer byteBuffer2 = ByteBuffer.wrap("00202".getBytes());
            ByteBuffer[] bs = new ByteBuffer[]{byteBuffer1, byteBuffer2};
            byteBuffer1.position(1);
            byteBuffer1.limit(3);
            byteBuffer2.position(1);
            byteBuffer2.limit(3);
            fileChannel.write(bs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 验证int read(ByteBuffer dst) 方法将字节放入ByteBuffer当前位置
     */
    private static void fileReader2() {
        // ababcd
        try (FileInputStream fileInputStream = new FileInputStream(new File(logPath)); FileChannel fileChannel = fileInputStream.getChannel();) {
            // 从索引2的位置开始读
            fileChannel.position(2);
            ByteBuffer byteBuffer = ByteBuffer.allocate(6);
            // byteBuffer 从索引3 的地方开始写入
            byteBuffer.position(3);
            fileChannel.read(byteBuffer);
            byte[] array = byteBuffer.array();
            for (byte b : array) {
                if (b == 0) {
                    System.out.print("空格\t");
                } else {
                    System.out.print((char) b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证 reader(ByteBuffer src)
     */
    private static void fileReader() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(logPath)); FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            int read = fileChannel.read(byteBuffer);
            System.out.println(read);
            byteBuffer.clear();
            // 再次执行read 返回值为0，因为byteBuffer中没有remaining空间
            System.out.println(fileChannel.read(byteBuffer));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * IO基础
     */
    private static void baseReader() {
        try (InputStream inputStream = Files.newInputStream(new File(logPath).toPath()); OutputStream outputStream = Files.newOutputStream(new File(targetPath).toPath())) {
            int len = -1;
            byte[] bytes = new byte[1024];
            String out = null;
            while ((len = inputStream.read(bytes)) > 0) {
                out += new String(bytes, 0, len);
                outputStream.write(bytes, 0, len);
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
        try (FileOutputStream fileOutputStream = new FileOutputStream(logPath); FileChannel channel = fileOutputStream.getChannel();) {
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
        try (FileOutputStream fosRef = new FileOutputStream(logPath); FileChannel fileChannel = fosRef.getChannel();) {
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
