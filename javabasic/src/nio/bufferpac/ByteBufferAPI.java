package nio.bufferpac;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static nio.bufferpac.DataConstraint.*;

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
//        api_putOrGet();
//        putByteArray();
//        api_putType();
//        api_slice();
//        api_asCharBuffer();
//        api_order();
//        api_compact();
//        api_compareTo();
//        api_duplicate();
        api_extendsCapacity();
    }

    private static void api_extendsCapacity() {
        ByteBuffer wrap = ByteBuffer.wrap(byteArray2);
        System.out.println("capacity is " + wrap.capacity() + ", position is " + wrap.position() + ", limit is " + wrap.limit());
        ByteBuffer newBuffer = extendsCapacity(wrap, 2);
        byte[] array = newBuffer.array();
        for (byte b : array) {
            System.out.print(b + " ");
        }
    }

    /**
     * 对容量进行扩容
     *
     * @param buffer
     * @param extendsSize
     */
    private static ByteBuffer extendsCapacity(ByteBuffer buffer, int extendsSize) {
        ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() + extendsSize);
        newBuffer.put(buffer);
        return newBuffer;
    }

    /**
     * 创建共享此缓冲区内容的新的字节缓冲区。新缓冲区的内容将为此缓冲区的内容，容量、限制、位置和标记的值与此缓冲区相同。
     */
    private static void api_duplicate() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        System.out.println("old byteBuffer capacity is " + byteBuffer.capacity() + ", limit is " + byteBuffer.limit() +
                ", position is " + byteBuffer.position());
        ByteBuffer byteBuffer_copy = byteBuffer.duplicate();
        System.out.println("copy byteBuffer capacity is " + byteBuffer_copy.capacity() + ", limit is " + byteBuffer_copy.limit() +
                ", position is " + byteBuffer_copy.position());
        System.out.println(byteBuffer.equals(byteBuffer_copy));
        byteBuffer_copy.put(1, (byte) 1);
        for (int i = byteBuffer.position(); i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get(i) + ", ");
        }
        System.out.println();
        for (int i = byteBuffer_copy.position(); i < byteBuffer_copy.limit(); i++) {
            System.out.print(byteBuffer_copy.get(i) + ", ");
        }
    }

    private static void api_compareTo() {
        byte[] b_arr = {3, 4, 5};
        byte[] b_arr2 = {1, 2, 3, 104, 5, 6, 7, 8, 9};
        ByteBuffer buffer1 = ByteBuffer.wrap(b_arr);
        ByteBuffer buffer2 = ByteBuffer.wrap(b_arr2);
        buffer1.position(0);
        buffer2.position(2);
        System.out.println("compare To " + buffer1.compareTo(buffer2));
    }

    /**
     * compact 压缩方法方法 将position后面的元素 移动到开始ide位置
     */
    private static void api_compact() {
        ByteBuffer wrapBuffer = ByteBuffer.wrap(byteArray);
        System.out.println("wrapBuffer capacity " + wrapBuffer.capacity() + ",position is " + wrapBuffer.position() + ",limit is " + wrapBuffer.limit());
        System.out.println("1 wrap buffer value is " + wrapBuffer.get());
        System.out.println("A wrapBuffer capacity " + wrapBuffer.capacity() + ",position is " + wrapBuffer.position() + ",limit is " + wrapBuffer.limit());
        System.out.println("2 wrap buffer value is " + wrapBuffer.get());
        System.out.println("B wrapBuffer capacity " + wrapBuffer.capacity() + ",position is " + wrapBuffer.position() + ",limit is " + wrapBuffer.limit());
        wrapBuffer.compact();
        System.out.println("C wrapBuffer capacity " + wrapBuffer.capacity() + ",position is " + wrapBuffer.position() + ",limit is " + wrapBuffer.limit());
        byte[] array = wrapBuffer.array();
        for (byte b : array) {
            System.out.print(b + " ");
        }
    }

    private static void api_order() {
        int value = 123456789;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.println(byteBuffer.order());
        byteBuffer.putInt(value);
        byte[] bufferArr = byteBuffer.array();
        for (byte b : bufferArr) {
            System.out.print(b + " ");
        }
        System.out.println();
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(4);
        byteBuffer1.order(ByteOrder.BIG_ENDIAN);
        System.out.println(byteBuffer.order());
        byteBuffer1.putInt(value);
        System.out.println(byteBuffer.position());
        byteBuffer1.position(0);
        byte[] bufferArr1 = byteBuffer.array();
        for (byte b : bufferArr1) {
            System.out.print(b + " ");
        }
        System.out.println("\n BIG_ENDIAN = " + byteBuffer1.order() + " == " + byteBuffer1.getInt());

        ByteBuffer byteBuffer2 = ByteBuffer.allocate(4);
        byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(byteBuffer2.order());
        byteBuffer2.putInt(value);
        byteBuffer2.position(0);
        byte[] bufferArr2 = byteBuffer2.array();
        for (byte b : bufferArr2) {
            System.out.print(b + " ");
        }
        System.out.println("\n LITTLE_ENDIAN = " + byteBuffer2.order());

    }

    /**
     * asCharBuffer 创建此字节缓冲区的视图，这两个缓冲区的位置、限制和标记值是相互独立的，
     * 新缓冲区的内容从此缓冲区的当前位置开始，新缓冲区的位置将为0，容量和限制将为此缓冲区所剩余的字节数的1/2
     * asDoubleBuffer() asLongBuffer() asFloatBuffer() asDoubleBuffer asIntBuffer() asShortBuffer()
     */
    private static void api_asCharBuffer() {
        //直接定义 编码格式
        byte[] bytes = "你好啊啊啊".getBytes(StandardCharsets.UTF_8);
        //运行本代码*.java 文件是 UTF-8， System.getProperty("file.encoding") 运行环境取得默认是UTF-8
        System.out.println(Charset.defaultCharset().name());
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
//        wrap.position(1);
        System.out.println("byteBuffer = " + wrap.getClass().getName());
//        CharBuffer charBuffer = wrap.asCharBuffer();
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(wrap);
        System.out.println("charBuffer = " + charBuffer.getClass().getName());
        System.out.println("wrap capacity is " + wrap.capacity() + ", limit is " + wrap.limit() + ", position is " + wrap.position());
        System.out.println("charBuffer capacity is " + charBuffer.capacity() + ", limit is " + charBuffer.limit() + ", position is " + charBuffer.position());
        charBuffer.position(0);
        for (int i = 0; i < charBuffer.limit(); i++) {
            //get() 使用的编码是UTF-16BE 编码并不对称造成 乱码
            System.out.print(charBuffer.get() + "");

        }

    }

    /**
     * slice()：创建新的字节缓冲区，内容是此缓冲区内容的共享子序列
     * 这两个缓冲区 的位置、限制和标记值是相互独立的。
     */
    private static void api_slice() {
        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.position(5);
        ByteBuffer sliceBuffer = byteBuffer.slice();
        System.out.println("byteBuffer capacity is " + byteBuffer.capacity() + ", limit is " + byteBuffer.limit() + ", position is " + byteBuffer.position());
        System.out.println("sliceBuffer capacity is " + sliceBuffer.capacity() + ", limit is " + sliceBuffer.limit() + ", position is " + sliceBuffer.position());
        sliceBuffer.put(0, (byte) 111);
        byte[] bytes1 = byteBuffer.array();
        byte[] bytes2 = sliceBuffer.array();
        for (int i = 0; i < bytes1.length; i++) {
            System.out.print(bytes1[i] + ", ");
        }
        System.out.println("original buffer array offset => " + byteBuffer.arrayOffset());
        for (int i = 0; i < bytes2.length; i++) {
            System.out.print(bytes2[i] + ", ");
        }
        // sliceBuffer 的第一个元素的位置 是 相对于 bytes 数组中索引值为5 的偏移
        System.out.println("slice buffer array offset => " + sliceBuffer.arrayOffset());
        System.out.println("slice " + sliceBuffer.get(0));
    }

    /**
     * putChar() putShort() putInt() putLong() putFloat() putDouble()
     */
    private static void api_putType() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        // char 占用2个字节 0-1
        byteBuffer.putChar('a');
        System.out.println("byteBuffer postion = " + byteBuffer.position());
        byteBuffer.putChar(2, 'b');
        byteBuffer.position(4);
//        // double 占用 8个字节 4 -11
        byteBuffer.putDouble(2.2);
//        //12-19
//        byteBuffer.putDouble(12, 1.1);

        for (int i = 0; i < byteBuffer.array().length; i++) {
            System.out.print(byteBuffer.array()[i] + ", ");
        }
//        System.out.println(byteBuffer.getDouble() + "====" + byteBuffer.getDouble(4));

    }

    /**
     * 当参数length的值大于buffer.remaing时，抛出BufferOverflowException异常
     */
    private static void putByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        /*
        byteBuffer.position(7);
        // 当参数length的值大于buffer.remaing时，抛出BufferOverflowException异常
        byteBuffer.put(byteArray,1,5);
        */
        // 解决：
        int writeBufferIndex = 0;
        while (writeBufferIndex < byteArray.length) {
            int readLength = Math.min(byteBuffer.remaining(), byteArray.length - writeBufferIndex);
            byteBuffer.put(byteArray, writeBufferIndex, readLength);
            // 将position 设置为limit
            byteBuffer.flip();
            byte[] array = byteBuffer.array();
            for (int i = 0; i < byteBuffer.limit(); i++) {
                System.out.print(array[i] + " ");
            }
            writeBufferIndex = writeBufferIndex + readLength;
            System.out.println();
            byteBuffer.clear();
        }
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
        allocate.put(byteArr_2, 1, 3);
        byte[] getBytes = allocate.array();
        for (int i = 0; i < getBytes.length; i++) {
            System.out.print(getBytes[i] + " ");
        }
        System.out.println();

        allocate.position(1);
        byte[] byteOutArr = new byte[allocate.capacity()];
        /*
            get(byte[] dst, int offset, int length);
            1. dst：将缓冲区中当前位置的数据写入dst 数组中
            2. offset：要写入的第一个字节在 ”导出数组中的偏移量“， 并”不是缓冲区的偏移量“， 必须为非负且不大于dst.length;
            3. length：要写入到给定数组中的字节的最大数量，必须为非负且不大于 dst.length - offset;
            获取索引为1 的 长度为4 的数据，存放到索引为3 的地方。
         */
        allocate.get(byteOutArr, 3, 4);
        for (int i = 0; i < byteOutArr.length; i++) {
            System.out.print(byteOutArr[i] + " ");
        }
        System.out.println("\n === put([] byte)  method begin ===");
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        byteBuffer.put((byte) 10);
        byteBuffer.put((byte) 11);
        System.out.println("A= " + byteBuffer.position());
        byteBuffer.put(byteArray);
        System.out.println("B = " + byteBuffer.position());
        byteBuffer.flip();
        byteBuffer.position(3);
        System.out.println("C = " + byteBuffer.position());
        byte[] newArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(newArray);
        for (int i = 0; i < newArray.length; i++) {
            System.out.print(newArray[i] + " ");
        }
        System.out.println("\n === put(index i ) method begin");
        ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.put(byteArray);
        buffer.put(1, (byte) 12);
        buffer.put(11, (byte) 14);
        System.out.println("A = " + buffer.position());
        byte b = buffer.get(1);
        System.out.println("B=" + b);
        byte[] bytesArr = new byte[buffer.capacity()];
        buffer.get(bytesArr, 0, 3);
        for (int i = 0; i < bytesArr.length; i++) {
            System.out.print(bytesArr[i] + " ");
        }
        System.out.println("\n put(Buffer src) method begin");
        ByteBuffer b1 = ByteBuffer.wrap(byteArray);
        byte[] bytes = new byte[]{11, 22, 33};
        ByteBuffer b2 = ByteBuffer.wrap(bytes);
        b1.position(2);
        b2.position(2);
        b1.put(b2);
        System.out.println("b1 position => " + b1.position());
        System.out.println("b2 position => " + b2.position());
        byte[] temp = b1.array();
        for (int i = 0; i < temp.length; i++) {
            System.out.printf("%s ", temp[i]);
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
