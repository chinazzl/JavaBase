package leetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: zhang zhao lin
 * @Date: Create in 2023/4/27 16:29
 * @Modified By：
 * @Description: 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 * <p>
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 * <p>
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 * <p>
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 */
public class Leetcode1048 {

    public static void main(String[] args) {
        String[] words = {"ba","a", "b",  "bca", "bda", "bdca"};
        System.out.println(longestStrChain(words));
    }

    /**
     * 示例 1：
     * <p>
     * 输入：words = ["a","b","ba","bca","bda","bdca"]
     * 输出：4
     * 解释：最长单词链之一为 ["a","ba","bda","bdca"]
     * 示例 2:
     * <p>
     * 输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
     * 输出：5
     * 解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
     * 示例 3:
     * <p>
     * 输入：words = ["abcd","dbqca"]
     * 输出：1
     * 解释：字链["abcd"]是最长的字链之一。
     * ["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
     *
     * @param words
     * @return
     */
    static int longestStrChain(String[] words) {
        int len = words.length;
        Arrays.sort(words,(s1,s2) -> {
            if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() < s2.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        int cn = 1;
        for (int i = 0; i < words.length -1; i++) {
            int asciiSumNext = asciiSum(words[i+1]);
            int asciiSumCur = asciiSum(words[i]);
            for (int j = 97; j <= 122; j++) {
                if (asciiSumCur + j == asciiSumNext && (words[i] + (char)j).equals(words[i + 1])) {
                    ++cn;
                    System.out.println(words[i]);
                    break;
                }
            }

        }
        return cn;
    }
    
    private static int asciiSum(String s) {
        char[] cs = s.toCharArray();
        int asciiSum = 0;
        for (char wc : cs) {
            asciiSum += wc;
        }
        return asciiSum;
    }
}
