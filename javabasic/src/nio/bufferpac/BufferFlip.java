package nio.bufferpac;

import java.nio.CharBuffer;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.bufferpac
 * @Description:
 * @Date: 2021/10/26 16:23
 */
public class BufferFlip {
    public static void main(String[] args) {
//        getSimilarFlips();
        getDateUseFlip();
    }

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
}
