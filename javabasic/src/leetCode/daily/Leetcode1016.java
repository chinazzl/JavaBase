package leetCode.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: zhang zhao lin
 * @Date: Create in 2023/5/11 10:15
 * @Modified By：LeetCode Daily 1016
 * @Description: 给定一个二进制字符串s和一个正整数n，如果对于[1, n]范围内的每个整数，其二进制表示都是s 的 子字符串 ，就返回 true，否则返回 false。
 * 子字符串是字符串中连续的字符序列。
 */
public class Leetcode1016 {

    public static void main(String[] args) {
        String s = "1001111111000111011100110001001011100001101011001000100000001111100100001101100010010100111111100010101000000001010101011111001010111100000001110000001111000100001101010011010111101101000101101001110011000110110000110010111011100100101111000010011111001000001001000011011000111110010001010110101110011010000101001110000010111100001111011110000100000011000111111011101001011110111001110000011100100101001100001100111010010111111111000011110001110100010001000000101110000010100100111100010001111111110100001111000101001111110000011000001000001110011011011100010101010111110101110101110010111000110111110";
        int n = 30;
        System.out.println(queryString(s, n));
    }

    public static boolean queryString(String s, int n) {
        Set<Integer> vs = slideSubStr(s);
        boolean r = true;
        for (int i = 1; i <= n; i++) {
            if (!vs.contains(i)) {
                r = false;
                break;
            }
        }
        if (s.length() == 1 && Integer.parseInt(s) == n) {
            r = true;
        }
        return r;
    }

    /**
     * 输入：s = "0110", n = 3
     * 输出：true
     *
     * @param binary
     * @return
     */
    private static Set<Integer> slideSubStr(String binary) {
        Set<Integer> vs = new HashSet<>();
        int len = binary.length();
        String s;
        for (int i = 0; i < len; i++) {
            s = String.valueOf(binary.charAt(i));
            for (int j = i + 1; j < len; j++) {
                s += binary.charAt(j);
                int subLen = s.length();
                int v = 0;
                for (int k = subLen - 1, f = 0; k >= 0 && f < subLen; k--, f++) {
                    v += (s.charAt(f) - '0') * (1 << k);
                }
                vs.add(v);
            }
        }
        return vs;
    }

}
