package leetCode.review;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/2/24 10:57
 */
public class ChangeFileds {
    public static void main(String[] args) throws NoSuchMethodException {
        int a = 10;
        int b = 10;
        // 输出 a =100 b =200
        method(a,b);
        System.out.println(a);
        System.out.println(b);
    }

    private static void method(int a, int b) throws NoSuchMethodException {
        Method main = ChangeFileds.class.getMethod("main", String[].class);
        System.out.println(main.getDeclaringClass().getFields().length);
    }


}
