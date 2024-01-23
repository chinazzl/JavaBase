package langer.finals;

import java.util.Arrays;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/15 10:34
 * @Modified By：
 */
public class FinalString {
    public static void main(String[] args) {
        int a = "abcd".hashCode();
        int b = -2;
        System.out.println(a > (a >> b));
        System.out.println(b >>> 1);
    }
}
