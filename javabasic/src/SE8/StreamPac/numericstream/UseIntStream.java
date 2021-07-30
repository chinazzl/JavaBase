package SE8.StreamPac.numericstream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/31 20:45
 * @Modified By：
 */
public class UseIntStream {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        //计算该stream中大于3的数字之和
        Integer reduce = stream.filter(i -> i > 3).reduce(0, (i, j) -> i + j);
        System.out.println("reduce ->" + reduce);
        Stream<Integer> stream_1 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        int sum = stream_1.mapToInt(i -> i.intValue()).filter(i -> i > 3).sum();
        System.out.println("intStream ->" + sum);

       /*
            勾股定理 取值[1,100]，
            满足 a^2 + b^2 = c^2的值
            返回 {[a,b,c],[a,b,c]...}
        */
        int a = 9;
        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(c -> new int[]{a, c, (int) Math.sqrt(a * a + c * c)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b= " + r[1] + ",c=" + r[2]));
        System.out.println("  --- ");

        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(c -> new int[]{a, c, (int) Math.sqrt(a * a + c * c)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b= " + r[1] + ",c=" + r[2]));

        //使用flatMap
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a1 -> IntStream.rangeClosed(a1, 100)
                        .mapToObj(b -> new double[]{a1, b, Math.sqrt(a1 * a1 + b * b)})
                        .filter(t -> t[2] % 1 == 0))
                .forEach(r -> {
                    System.out.println("a=" + r[0] + ",b= " + r[1] + ",c=" + r[2]);
                });

    }

}
