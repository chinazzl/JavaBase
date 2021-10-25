package base;

import org.junit.Test;

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
}
