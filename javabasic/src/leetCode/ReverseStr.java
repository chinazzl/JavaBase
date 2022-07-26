package leetCode;

/**********************************
 * @author zhang zhao lin
 * @date 2022年07月25日 21:46
 * @Description: 编写一段Java程序, 把一句英语中的每个单词中的字母次序倒转,单词次序保持不变.
 * 例如输入为 “There is a dog.”, 输出结果应该是”erehT si a god.”
 * 要求不使用Java的库函数，例如String类的split, reverse方法.
 * 函数形如:
 * String reverseWords(String input) {
 * }
 **********************************/
public class ReverseStr {
    public static void main(String[] args) {
        String input = "There is a dog.";
        String result = reverseStr(input);
        System.out.println(result);
    }

    private static String reverseStr(String input) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length() - 1; i++) {
            char c = input.charAt(i);
            if (' ' != c) {
                sb.append(c);
            } else {
                String word = reverseWord(sb);
                sb.setLength(0);
                result.append(word).append(" ");
            }
        }
        String word = reverseWord(sb);
        result.append(word).append(".");
        return result.toString();
    }

    private static String reverseWord(StringBuilder sb) {
        char[] words = new char[sb.length()];
        int len = words.length;
        for (int i = len - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            words[len - 1 - i] = c;
        }
        return new String(words);
    }
}
