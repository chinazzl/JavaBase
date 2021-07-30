package SE8.LambdaPac;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2019/12/19 22:51
 * @Modified By：
 */
public class LambdaDemo_2 {
    public static void main(String[] args) {
        Function<String,Integer> functionLambda = s -> s.length();
        System.out.println(functionLambda.apply("adsjf"));

        String s = "1";
        consumeString(s,(c) -> {System.out.println(c);});

    }

    private static void consumeString(String str,Consumer<String> function) {
        function.accept(str);

    }
}
