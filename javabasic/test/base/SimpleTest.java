package base;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package base
 * @Description:
 * @Date: 2021/10/19 10:46
 */
public class SimpleTest {

    @Test
    public void simple1() {
        int i = 1;
        int b = i;
        i = 3;
        System.out.println(i + " == " + b);
    }

    @Test
    public void fileChannel_1() {
        File file = new File("C:\\Users\\Julyan\\Desktop\\a.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             FileChannel fileChannel = fos.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.wrap("abcd".getBytes());
            System.out.println("A fileChannel position => " + fileChannel.position());
            System.out.println("write() 1 返回值：" + fileChannel.write(byteBuffer));
            System.out.println("B fileChannel position => " + fileChannel.position());
            fileChannel.position(2);
            byteBuffer.rewind();
            System.out.println("write() 2 返回值 => " + fileChannel.write(byteBuffer));
            System.out.println("C fileChannel position => " + fileChannel.position());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readChannelMoreThanBufferCapacity() {
        File file = new File("C:\\Users\\Julyan\\Desktop\\a.txt");
        try (FileInputStream fis = new FileInputStream(file);
             FileChannel fileChannel = fis.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(3);
            System.out.println(" file position => " + fileChannel.position());
            fileChannel.read(byteBuffer);
            System.out.println("Read After file position => " + fileChannel.position());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mathRound() {
        System.out.println(Math.round(-1.2));
        System.out.println(Math.round(1.2));
        double d = 20;
        System.out.println(d/100);
        int i = 10;
        System.out.println(i/d);
    }
}
