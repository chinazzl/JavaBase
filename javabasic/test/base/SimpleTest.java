package base;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
        System.out.println(d / 100);
        int i = 10;
        System.out.println(i / d);
    }

    @Test
    public void testCeil() {
        double d = 9;
        double ceil = Math.ceil(d);
        System.out.println(ceil);
    }

    @Test
    public void testRanged() {
        ArrayList<Double> cpuDatas = Lists.newArrayList(5.0, 3.0, 1.1, 2.2, 6.6, 4.1, 7.1);
        // 获取这段时间性能数据的最大值
        Double maxCPU = cpuDatas.stream().max(Double::compareTo).get();
        // 根据样本最大值计算五个区间范围
        long v = Math.round((maxCPU / 5));
        // 将时间段的性能数据分别分布在不同的区间[0,0+v], [0+v+0.01, 0+v+0.01+v] ...
        Multimap<String, Double> rangeMap = ArrayListMultimap.create();
        for (Double cpu : cpuDatas) {
            int start = 0, rangeName = 1;
            double rangeStart = 0.0;
            while (rangeStart < Math.ceil(maxCPU)) {
                if (cpu >= rangeStart + 0.01 && cpu <= (int) (rangeStart + v)) {
                    rangeMap.put(String.valueOf(rangeName), cpu);
                    break;
                }
                rangeStart = rangeStart + v;
                rangeName++;
            }
        }
        System.out.println(rangeMap);
    }

    @Test
    public void getTrainData() {
        File file = new File("data.txt");
        String ip = "102.200.218.193";
        StringBuilder sb = new StringBuilder("ip,time,usage\n");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try (BufferedOutputStream bfs = new BufferedOutputStream(new FileOutputStream(file, true));) {
            for (int i = 0; i < 2000; i++) {
                int nextInt = ThreadLocalRandom.current().nextInt(0, 100);
                now = now.minusSeconds(1);
                double next;
                if (nextInt/2 ==0) {
                    next = ThreadLocalRandom.current().nextDouble(10);
                } else {
                    next = ThreadLocalRandom.current().nextDouble(50);
                }

                sb.append(ip).append(",").append(now.format(dateTimeFormatter)).append(",").append(next).append("\n");
            }
            bfs.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void tetMapCapacity() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String,Integer> m = new HashMap<String,Integer>(4);
        Class<? extends Map> aClass = m.getClass();
        Method capacity = aClass.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        m.put("a", 1);
        System.out.println("存入第一个元素的capacity" + capacity.invoke(m) + " size=" + m.size());
        m.put("b", 1);
        System.out.println("存入第二个元素的capacity" + capacity.invoke(m) + " size=" + m.size());
        m.put("c", 1);
        System.out.println("存入第三个元素的capacity" + capacity.invoke(m) + " size=" + m.size());
        m.put("d", 1);
        System.out.println("存入第四个元素的capacity" + capacity.invoke(m) + " size=" + m.size());
    }

    @Test
    public void testAB() {
        Egg e = new Egg();
        e.haveNiceDinner("egg");
    }

    @Test
    public void tesWhile() {
        int page= 0;
        while (page < 4) {
            //page++;
            int i = page++;
            System.out.println(i);
            System.out.println(page);
        }
    }

    @Test
    public void testPrivateCOnstruct() {

    }

    @Test
    public void testParseLong() {
        String sql = "SELECT * FROM TABLE WHERE NAME IS NULL AND PASSWORD IS NULL";
        String[] split = sql.split("(?i)where");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length -1; i++) {
            if (i != split.length - 2) {
                sb.append(split[i] + " where ");
            } else {
                sb.append(split[i]);
            }
        }

        System.out.println(sb);

    }
}
