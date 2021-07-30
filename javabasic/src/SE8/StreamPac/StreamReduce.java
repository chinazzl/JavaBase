package SE8.StreamPac;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/27 21:02
 * @Modified By：
 * 学习reduce
 */

public class StreamReduce {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 9});
        Optional<Integer> reduce = stream.reduce((i, j) -> i + j);
        //有初始值，相当于 初始值 + stream计算的值
        Integer reduce1 = stream.reduce(1, (i, j) -> i + j);
        System.out.println(reduce.get());

    }
}
