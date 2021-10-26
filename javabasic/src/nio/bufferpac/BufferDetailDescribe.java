package nio.bufferpac;

import langer.Ex;

import java.nio.Buffer;
import java.nio.CharBuffer;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.bufferpac
 * @Description:
 * @Date: 2021/10/26 9:33
 */
public class BufferDetailDescribe {

    public static void main(String[] args) {
        //  1. 缓冲区capacity 不能为负数， 缓冲区的position 不能为负数 java.lang.IllegalArgumentException
//        Buffer charBuffer = getCharBuffer(-1, 0, 0, false);
        Buffer charBuffer = getCharBuffer(6, 6, 0, false);
        //2. position 不能大于其 limit
        /*charBuffer.limit(3);
        // java.lang.IllegalArgumentException
        charBuffer.position(4);*/
//        System.out.println(" new buffer capacity => " + charBuffer.capacity() + "; limit => " + charBuffer.limit() + "; position => " + charBuffer.position());
        System.out.println();
        //3. limit 不能大于其 capacity
        //java.lang.IllegalArgumentException
//        charBuffer.limit(100);
        //4. 如果定义了mark，则在将position或limit调整 小于该mark的值时，该mark 被丢弃；
        markAndLimit(charBuffer);
        //5. 如果未定义 mark，那么 调用reset方法将导致 异常
        //6. 如果 position 大于新的limit，则 position 的值就是新limit值
        charBuffer.position(3);
        charBuffer.limit(2);
        System.out.println("buffer capacity => " + charBuffer.capacity() + "; limit => " + charBuffer.limit() + "; position => " + charBuffer.position());

    }

    /**
     * @param capacity 容量 buffer.length
     * @param limit    限制  从limit 索引开始是不允许写入读取 数据
     * @param position 位置   从position 开始进行写入、读取 数据
     * @param mark     标志位 设置mark开始 可以调用reset方法，相当于探险队 探险后 说服对方的身份按原路返回。
     */
    private static Buffer getCharBuffer(int capacity, int limit, int position, boolean mark) {
        //分配新的 缓冲区
        CharBuffer charBuffer = CharBuffer.allocate(capacity);
        charBuffer.limit(limit);
        charBuffer.position(position);
        if (mark) {
            charBuffer.mark();
        }
        System.out.println("buffer capacity => " + charBuffer.capacity() + "; limit => " + charBuffer.limit() + "; position => " + charBuffer.position());
        return charBuffer;
    }

    /**
     * 1. 如果定义了 mark，则在将position 调整为不小于该 mark的值时，该mark 不丢弃
     * 2. 如果定义了mark， 则在将position 调整为小于该mark 的值时， 该mark 被丢弃
     * 3. 如果定义了mark， 则在将limit调整为不小于该mark的值时，该mark 不丢弃。
     * 4. 如果定义了mark， 则在将limit调整为小于该mark的值时，该 mark 被丢弃。
     *
     * @param buffer nio buffer
     */
    private static void markAndLimit(Buffer buffer) {
        System.out.println("========================1. 如果定义了 mark，则在将position 调整为不小于该 mark的值时，该mark 不丢弃========================");
        buffer.position(3);
        buffer.mark();
        System.out.println("buffer 在" + buffer.position() + "的位置上设置 mark 标记");
        buffer.position(4);
        buffer.reset();
        System.out.println("buffer 在" + buffer.position() + "的位置上 调用reset方法，设置 mark 标记, mark 没有丢弃");
        System.out.println("=======================2. 如果定义了mark， 则在将position 调整为小于该mark 的值时， 该mark 被丢弃=========================");
        buffer.position(2);
        try {
            buffer.reset();
        } catch (Exception e) {
            // mark丢弃后 position = 2
            System.out.println("buffer 在" + buffer.position() + "的位置上 position 调整为小于该mark 调用reset方法，设置 mark 标记, mark 丢弃");
        }
        System.out.println("=======================3. 如果定义了mark， 则在将limit调整为不小于该mark的值时，该mark 不丢弃。=========================");
        buffer.mark();
        buffer.limit(3);
        buffer.reset();
        System.out.println("buffer 在" + buffer.position() + "的位置上 调用reset方法，limit调整为不小于该mark的值，设置 mark 标记, mark 没有丢弃");
        System.out.println("=======================4. 如果定义了mark， 则在将limit调整为小于该mark的值时，该 mark 被丢弃。=========================");
        buffer.limit(1);
        try {
            buffer.reset();
        } catch (Exception e) {
            System.out.println("buffer 在" + buffer.position() + "的位置上 limit调整为小于该mark的值时 调用reset方法，设置 mark 标记, mark 丢弃");
        }
    }

}
