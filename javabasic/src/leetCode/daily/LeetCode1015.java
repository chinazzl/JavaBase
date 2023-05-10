package leetCode.daily;

/**
 * @author: zhang zhao lin
 * @Date: Create in 2023/5/10 17:05
 * @Modified By：
 * @Description: 可被 K 整除的最小整数
 *
 * (a + b) mod m
 * = ((k1 +k2)m+r1+r2) mod m
 * = (r1 + r2) mod m
 * = ((a mod m)+(b mod m))mod m
 *
 *
 */
public class LeetCode1015 {

    public static void main(String[] args) {

    }

    /**
     * 给定正整数 k，你需要找出可以被 k整除的、仅包含数字 1 的最 小 正整数 n的长度。
     * <p>
     * 返回 n的长度。如果不存在这样的 n，就返回-1。
     * <p>
     * 注意： n不符合 64 位带符号整数。
     * <p>
     * 示例 1：
     * <p>
     * 输入：k = 1
     * 输出：1
     * 解释：最小的答案是 n = 1，其长度为 1。
     * 示例 2：
     * <p>
     * 输入：k = 2
     * 输出：-1
     * 解释：不存在可被 2 整除的正整数 n 。
     * 示例 3：
     * <p>
     * 输入：k = 3
     * 输出：3
     * 解释：最小的答案是 n = 111，其长度为 3。
     *
     * @param k
     * @return
     */
    public static int smallestRepunitDivByK(int k) {
        int n = 1 % k, b;
        for (int i = 1; i <= k; i++) {
            if (n == 0) {
                return i;
            }
            n = ( n * 10 + 1) % k;

        }
        return -1;
    }
}
