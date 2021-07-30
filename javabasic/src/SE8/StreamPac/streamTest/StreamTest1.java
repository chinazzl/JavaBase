package SE8.StreamPac.streamTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/25 21:35
 * @Modified By：
 *
 */
public class StreamTest1 {
    public static void main(String[] args) {
        /*
        给定一个数字列表，如何返回一个由每个数的平方后成的列表呢？
 * 如[ 1,2,3,4,5] 应该返回 [ 1,4,9,16,25]
         */
        Stream<Integer> integetStream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5});
        List<Integer> collect = integetStream.map(i -> i * i).collect(Collectors.toList());


        /*
            给定列表[1,2,3] 和 [ 3,4]
            返回 [(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
         */
        Stream<Integer> stream_1 = Arrays.stream(new Integer[]{1});
        Stream<Integer> stream = Arrays.stream(new Integer[]{3, 4});

        List<Integer[]> collect1 = stream_1.flatMap(i -> stream.map(j -> new Integer[]{i, j})).collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }
}
