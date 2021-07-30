package SE8.StreamPac;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/26 20:48
 * @Modified By：
 *  Match find
 */
public class StreamMatch {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 10});
        //所有的值都大于9
        boolean b = stream.allMatch(i -> i > 9);
        System.out.println(b);

        //只要一个小于9
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 10});
        boolean b1 = stream.anyMatch(i -> i < 9);
        System.out.println(b1);

        //没有一个 小于0的
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 10});
        boolean b2 = stream.noneMatch(i -> i < 0);
        System.out.println(b2);

    }
}
