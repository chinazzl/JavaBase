package SE8.StreamPac;

import SE8.Predicate;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/27 20:36
 * @Modified By：
 * 学习Find
 */
public class StreamFind {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 9});
        //findAny是从结果流中随机选取符合条件的，结果是不确定的
        Integer integer = stream.filter(i -> i > 2).findAny().get();
        System.out.println(integer);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 9});
        //如果确定的请使用 findFirst
        Integer integer1 = stream.filter(i -> i > 2).findFirst().get();
        System.out.println(integer1);

        Integer integer2 = find(new Integer[]{1, 2, 3, 4, 5, 9}, -1, i -> i > 12);
        System.out.println(integer2);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 9});
        Integer integer3 = stream.filter(i -> i > 12).findAny().orElse(-1);
        System.out.println("integer3  测试 orElse : " + integer3);


        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 9});
        //判断是否存在
        boolean present = stream.filter(i -> i > 10).findAny().isPresent();
        System.out.println(present);


    }

    private static Integer find(Integer[] integers, int defaultVal, Predicate<Integer> predicate) {
        for (Integer i : integers) {
            if (predicate.test(i)) {
                return i;
            }
        }
        return defaultVal;
    }
}
