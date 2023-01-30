package guavaUtil;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/12/8 22:03
 * @Description:
 */
public class GuavaJoiner {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        StringBuilder stringBuilder1 = Joiner.on(",").appendTo(stringBuilder, list);
        System.out.println(stringBuilder1);

        // 相当于
        String collect = list.stream().collect(Collectors.joining(",", "", ""));
        System.out.println(collect);

    }
}
