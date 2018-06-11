package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。

 示例：

 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串
 */
public class DuplineStr {
    public static void main(String[] args) {
        String s = "ywwkew";
        StringSolution stringSolution = new StringSolution();
        int i = stringSolution.lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}

class StringSolution {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            String endStr = "";
            for (int j = i+1; j < chars.length; j++) {
                if (chars[j-1] == chars[j]){
                    list.add(String.valueOf(chars[j-1]));

                }else{
                    char c1 = chars[j - 1];
                    String s1 = String.valueOf(c1);
                    char c2 = chars[j];
//                     endStr= s1;
                    if(c2 == endStr.charAt(endStr.length()-1)){
                        list.add(endStr);
                        continue;
                    }else{
                        endStr = endStr + String.valueOf(c2);
                    }

                }
                list.add(endStr);
            }

        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return 0;
    }
}