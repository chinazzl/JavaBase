package SE8.StreamPac;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/22 12:56
 * @Modified By：
 */
public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 5,6,6,7,2,1,4,2);
        //filter过滤
        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println(result);
        //去重stream
        result = list.stream().distinct().collect(toList());
        System.out.println(result);

        //skip，跳过
        result = list.stream().skip(5).collect(toList());
        System.out.println(result);

        //limit, 截取
        result = list.stream().limit(5).collect(toList());
        System.out.println(result);

        System.out.println((3* 0.1) == 0.3);
        String a = "123";
        String b = "1";
        String c = b +"23";
        System.out.println(a == c);
        String d = "1" + "23";
        System.out.println(a ==d);
        System.out.println(Math.round(1.5));
    }
}
