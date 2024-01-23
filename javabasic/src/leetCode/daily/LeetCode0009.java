package leetCode.daily;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**********************************
 * @author zhang zhao lin
 * @date 2023年10月24日 17:31
 * @Description:
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 **********************************/
public class LeetCode0009 {

    public static void main(String[] args) {
        String startTime = "2023-11-01 12:00:12";
        String endTime = "2023-11-01 12:02:34";


        Map<String, Set<Integer>> m = new HashMap<>();

        m.put("1", Sets.newHashSet(1, 2));
        m.put("2", Sets.newHashSet(3, 4));
        m.put("3", Sets.newHashSet(3, 5));

// 获取Map的最大key
        int maxKey = m.keySet().stream().mapToInt(Integer::parseInt).max().orElse(0);

// 汇聚key大于1的值
        Set<Integer> resultSet = m.entrySet().stream()
                .filter(entry -> Integer.parseInt(entry.getKey()) > 1)
                .map(Map.Entry::getValue)
                .flatMap(set -> set.stream())
                .collect(Collectors.toSet());

// 构造结果Map
        Map<String, Set<Integer>> result = new HashMap<>();
        result.put(String.valueOf(maxKey), resultSet);

        System.out.println(result);
    }
}
