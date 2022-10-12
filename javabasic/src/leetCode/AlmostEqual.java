package leetCode;

/**********************************
 * @author zhang zhao lin
 * @date 2022年10月11日 22:35
 * @Description: 1790. 仅执行一次字符串交换能否使两个字符串相等
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），
 * 并交换这两个下标所对应的字符。
 * 如果对 其中一个字符串 执行最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 **********************************/
public class AlmostEqual {
    public static void main(String[] args) {
        // bank kanb true
        // ab ba true
        // ac aa false
        //adsfasdf ffsadf false
        System.out.println(areAlmostEqual("bank", "kanb"));
        System.out.println(areAlmostEqual("ab", "ba"));
        System.out.println(areAlmostEqual("ac", "aa"));
        System.out.println(areAlmostEqual("asdfasdf", "kasdfsdfsdanb"));
        System.out.println(areAlmostEqual("a", "b"));
        System.out.println(areAlmostEqual("kelb", "kelb"));
    }

    /**
     * 思路：
     * 1. 遍历两个字符如果字符都相等，则返回true
     * 2. 如果不相等则最多一次交换肯定是最多两个字符不同并且s2[j] = s1[i]; s1[j] = s2[i]
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean areAlmostEqual(String s1, String s2) {
        int count = 0;
        int m = -1;
        int n = -1;
        for (int i = 0; i < s1.length(); i++) {
            // 获取两个交换的字符的角标
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (m == -1) {
                    m = i;
                }
                if (n == -1 && m != i) {
                    n = i;
                }
            }
        }
        if ( count == 0) {
            return true;
        }
        return count == 2 && s1.charAt(m) == s2.charAt(n) && s2.charAt(m) == s1.charAt(n);
    }
}
