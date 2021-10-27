package nio.bufferpac;

import java.nio.ByteBuffer;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.bufferpac
 * @Description: Byte Buffer API
 * @Date: 2021/10/27 14:18
 */
public class ByteBufferAPI {

    public static void main(String[] args) {
//        buildByteBuffer();
        api_putOrGet();
    }

    /**
     * 使用byteBuffer 的put 新增数据
     * 1. put(val)
     * 2. put(byte[] src, int offset, int length)
     * 3.
     */
    private static void api_putOrGet() {
        byte[] byteArr_1 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] byteArr_2 = new byte[]{11, 22, 33, 44, 55};
        //开辟10个
        ByteBuffer allocate = ByteBuffer.allocate(10);
        //将 9个元素 放入缓冲区的9个位置
        allocate.put(byteArr_1);
        allocate.position(2);
        /*
            将11, 22, 33, 44, 55 的22, 33, 44, 55 放入缓冲区的第三位
            值变为1，2，22，33，44，6，7，8，9
            src：缓冲区中当前位置的数据来自于 src数组
            offset：要读取的第一个字节在“数组中的偏移量”，“不是缓冲区的偏移量”，必须是非负数且不大于src.length
            length：要从给定数组读取的字节的数量，必须为非负数且不大于src.length - offset
         */
        allocate.put(byteArr_2,1,3);
        byte[] getBytes = allocate.array();
        for (int i = 0; i < getBytes.length; i++) {
            System.out.print(getBytes[i] + " ");
        }
        System.out.println();

        allocate.position(1);
        byte[] byteOutArr  = new byte[allocate.capacity()];
        /*
            get(byte[] dst, int offset, int length);
            1. dst：将蝗虫去中当前位置的数据写入dst 数组中
            2. offset：要写入的第一个字节在 ”数组中的偏移量“， 并”不是缓冲区的偏移量“， 必须为非负且不大于dst.length;
            3. length：要写入到给定数组中的字节的最大数量，必须为非负且不大于 dst.length - offset;
            获取索引为1 的 长度为4 的数据，存放到索引为3 的地方。
         */
        allocate.get(byteOutArr,3,4);
        for (int i = 0; i < byteOutArr.length; i++) {
            System.out.print(byteOutArr[i] + " ");
        }

    }


    /**
     * 直接缓冲区 会比 非直接缓冲区运行效率会很快，因为 非直接缓冲区中间没有缓冲层。
     * 直接缓冲区直接和硬件进行I/O 操作。
     * 直接缓冲区 创建使使用 DirectByteBuffer 类 进行实现的，底层使使用 Unsafe的putByte实现的，直接JvM和 操作系统 直接通信。
     * 非直接缓冲区：使用 HeapByteBuffer 的 put(val) 方法，在内部维护了一个byte[] hb 字节数组进行操作 运行稍慢。
     */
    private static void buildByteBuffer() {
        //创建一个非直接字节缓冲区，新缓冲区的位置为0，界限为其容量
        ByteBuffer allocate = ByteBuffer.allocate(100);
        //创建一个直接自费缓冲区，新缓冲区的位置为0，界限为其容量，它将具有一个底层实现数组
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(200);
        System.out.println("ByteBuffer allocate has build. The className is " + allocate.getClass().getName() +
                " The capacity => " + allocate.capacity() + "; position is " + allocate.position() +
                "; limit is " + allocate.limit() + "; is array ? => " + allocate.hasArray());

        System.out.println("ByteBuffer allocateDirect has build.  The className is " + allocate.getClass().getName() +
                " The capacity => " + allocateDirect.capacity() + "; position is " + allocateDirect.position() +
                "; limit is " + allocate.limit() + "; is array ? => " + allocateDirect.hasArray());
        /*
            根据 byte[] 进行创建 byteBuffer
            array：缓冲区关联字节数组
            offset：设置position 的值，必须为非负且不大于 array.length的值
            length：将新缓冲区的界限（limit）设置为 offset + length，该值必须为非负且不大于array.length - offset;
         */
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bytes, 2, 2);
        System.out.println("ByteBuffer byteBufferWrap has build.  The className is " + byteBufferWrap.getClass().getName() +
                " The capacity => " + byteBufferWrap.capacity() + "; position is " + byteBufferWrap.position() +
                "; limit is (offset + length) " + byteBufferWrap.limit() + "; is array ? => " + byteBufferWrap.hasArray());
    }
}
