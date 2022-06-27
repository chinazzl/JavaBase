package nio.bufferpac;

import java.nio.*;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.bufferpac
 * @Description:
 * @Date: 2021/10/25 15:45
 */
public class chapter1 {

    public static void main(String[] args) {
        bufferedCapacityAndLimit();
    }

    public static void bufferedCapacityAndLimit() {
        char[] chars = new char[]{'a', 'b', 'c', 'd','e','f'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        System.out.println(charBuffer.limit() + ", capacity => " + charBuffer.capacity());
        //System.out.println(charBuffer);
        // 如果重新插入数据，则会覆盖指定的索引下的字符，如果没有达到capacity，则不会覆盖，所以需要限制limit
        // limit 为 capacity+1;
        charBuffer.limit(3);
        charBuffer.put(0,'1');
        charBuffer.put(1,'2');
        charBuffer.put(2,'3');
        // 调用put方法，如果put后直接获取buffer的数据，则会从put后的位置进行读取
        /*charBuffer.put('1');
        charBuffer.put('2');
        charBuffer.put('3');
        charBuffer.position(0);*/
        System.out.println(charBuffer);
    }

    public static void beginLearnNIO() {
        //byte 1字节 8bit
        byte[] bytes = new byte[]{1, 2, 3};
        //float 浮点型 4字节 32bits
        float[] floats = new float[]{1, 2, 3, 4};
        //short 2字节 16 bits
        short[] shorts = new short[]{1, 2, 3, 4, 5};
        //long 长整型 8字节 64bits
        long[] longs = new long[]{1, 2, 3, 4, 5, 6};
        // int 整型 4字节 32bits
        int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7};
        //double 双精度 8字节 64bits
        double[] doubles = new double[]{1, 2, 3, 4, 5, 6, 7, 8};
        // char类型 2个字节 16位 Unicode 字符
        char[] chars = new char[]{'a', 'b', 'c', 'd'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shorts);
        IntBuffer intBuffer = IntBuffer.wrap(ints);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubles);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floats);
        LongBuffer longBuffer = LongBuffer.wrap(longs);
        CharBuffer charBuffer = CharBuffer.wrap(chars);

        System.out.println(byteBuffer.getClass().getName());
        System.out.println(shortBuffer.getClass().getName());
        System.out.println(intBuffer.getClass().getName());
        System.out.println(doubleBuffer.getClass().getName());
        System.out.println(floatBuffer.getClass().getName());
        System.out.println(longBuffer.getClass().getName());
        System.out.println(charBuffer.getClass().getName());

        System.out.println(byteBuffer.capacity());
    }
}
