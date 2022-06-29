package nio.bufferpac;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.List;

import static nio.bufferpac.DataConstraint.*;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.bufferpac
 * @Date: 2021/10/27 10:11
 * @Description: Buffer 缓冲区 api 方法
 * - rewind：使用缓冲区为“重新读取” 已包含的数据做好准备，它使限制保持不变，将位置设置为0。相当于重新读取。
 * 一般用于 重新读取缓冲区中数据时使用
 * @see BufferAPI_Base#rewindBuffer()
 * - clear： Buff使缓冲区为一系列新的通道读取或相对put(value) 操作做好准备，即它将限制设置为容量大小，将位置设置为0。相当于 还原一切，
 * 一般用于对缓冲区进行存储数据之前调用此方法。
 * @see BufferAPI_Base#clearBuffer()
 * - flip：使缓冲区为一系列新的通道写入或相对get(value) 操作做好准备，即它将限制设置为当前位置，然后将位置设置为0。也就是截取
 * 一般缩小limit的范围
 * @see BufferAPI_Base#getDateUseFlip()
 */
public class BufferAPI_Base {

    public static void main(String[] args) {
//        getRemainingBuffer();
//        rewindBuffer();
//        bufferList2Array();
//        clearBuffer();
        listtoBufferArray();
    }

    /**
     * 如果List 中存储 Buffer 数据类型，则可以使用List 中toArray() 方法转成ByteBuffer[] 数组类型。
     */
    private static void bufferList2Array() {
        char[] array = new char[]{'1', '2', '3', '4', '5'};
        char[] array2 = new char[]{'a', 'b', 'c', 'd', 'e'};
        CharBuffer charBuffer = CharBuffer.wrap(array);
        CharBuffer charBuffer2 = CharBuffer.wrap(array2);
        List<CharBuffer> list = new ArrayList<>();
        list.add(charBuffer2);
        list.add(charBuffer);
        CharBuffer[] buffers = new CharBuffer[list.size()];
        list.toArray(buffers);
        System.out.println(buffers.length);
        for (int i = 0; i < buffers.length; i++) {
            CharBuffer cb = buffers[i];
            //判断一下 当前的位置 和 限制中间是否有数据
            while (cb.hasRemaining()) {
                System.out.println(cb.get());
            }
            System.out.println();
        }

    }

    /**
     * clear方法 将缓冲区的状态进行还原，包含将position归0，再执行写入新的数据的代码，
     * 将最新版的数据由索引位置0开始u覆盖。相当于还原一切
     */
    private static void clearBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.position(2);
        byteBuffer.limit(3);
        byteBuffer.mark();
        byteBuffer.clear();
        System.out.println("byteBuffer.position=>" + byteBuffer.position());
        System.out.println("byteBuffer.limit => " + byteBuffer.limit());
        System.out.println("byteBuffer.capacity => " + byteBuffer.capacity());
        try {
            byteBuffer.reset();
        } catch (InvalidMarkException e) {
            System.out.println("byteBuffer mark 丢失");
        }
    }

    /**
     * 重绕缓冲区
     * 将当前位置重置0 mark 标志为-1 limit不变
     */
    private static void rewindBuffer() {
        char[] array = new char[]{'1', '2', '3', '4', '5'};
        CharBuffer charBuffer = CharBuffer.wrap(array);
        charBuffer.limit(4);
        charBuffer.position(2);
        System.out.println("array capacity is => " + charBuffer.capacity() + "; limit => " + charBuffer.limit() +
                "; position => " + charBuffer.position());
        charBuffer.rewind();
        System.out.println("array after rewind capacity is => " + charBuffer.capacity() + "; limit => " + charBuffer.limit() +
                "; position => " + charBuffer.position());
    }

    /**
     * 判断 当前位置 与 限制之间 的元素个数
     */
    private static void getRemainingBuffer() {
        char[] array = new char[]{'1', '2', '3', '4', '5'};
        CharBuffer charBuffer = CharBuffer.wrap(array);
        charBuffer.limit(4);
        charBuffer.position(2);
        int remaining = charBuffer.remaining();
        System.out.println("array capacity is => " + charBuffer.capacity() + "; limit => " + charBuffer.limit() +
                "; position => " + charBuffer.position() + "; 相差元素的个数 => " + remaining + " " + charBuffer.get());
    }

    /**
     * 使用flip 进行缩小limit的范围
     */
    private static void getDateUseFlip() {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("This Buffer capacity => " + charBuffer.capacity() + " limit is " + charBuffer.limit() +
                ", position => " + charBuffer.position());
        charBuffer.put("12345678");
        charBuffer.flip();
        System.out.println("This Buffer  after flip capacity => " + charBuffer.capacity() + " limit is " + charBuffer.limit() +
                ", position => " + charBuffer.position());
        StringBuilder sb = new StringBuilder();
        sb.append("use flip => ");
        for (int i = 0; i < charBuffer.limit(); i++) {
            sb.append(charBuffer.get()).append(", ");
        }
        System.out.println(sb);
    }

    /**
     * 模拟 Flip方法
     */
    private static void getSimilarFlips() {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("This Buffer capacity => " + charBuffer.capacity() + " limit is " + charBuffer.limit());
        charBuffer.put("12345678");
        charBuffer.limit(charBuffer.position());
        charBuffer.position(0);
        System.out.println("This Buffer had put capacity => " + charBuffer.capacity() + " limit is " + charBuffer.limit());
        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get() + ", ");
        }
    }

    private static void listtoBufferArray() {
        ByteBuffer b1 = ByteBuffer.wrap(new byte[]{'a', 'b', 'c'});
        ByteBuffer b2 = ByteBuffer.wrap(new byte[]{'1', '2', '3', '4'});
        ByteBuffer b3 = ByteBuffer.wrap(new byte[]{'x', 'y', 'z'});
        List<ByteBuffer> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        ByteBuffer[] buffers = new ByteBuffer[list.size()];
        ByteBuffer[] list2Buffers = list.toArray(buffers);
        for (ByteBuffer byteBuffer : list2Buffers) {
            while (byteBuffer.hasRemaining()) {
                System.out.printf("%s", (char) byteBuffer.get());
            }
            System.out.println();
        }
    }
}
