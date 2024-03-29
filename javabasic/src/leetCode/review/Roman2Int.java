package leetCode.review;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**********************************
 * @author zhang zhao lin
 * @date 2022年10月10日 23:02
 * @Description: 罗马字母转数字
 *,罗马数字包含以下七种字符:I.V，X，L，C，D和M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 **********************************/
public class Roman2Int {

    public static void main(String[] args) {
        System.out.println(romanToInt("XX"));
    }

    private static int romanToInt(String roman) {
        roman = roman.replaceAll("IV", "a");
        roman = roman.replaceAll("IX", "b");
        roman = roman.replaceAll("XL", "c");
        roman = roman.replaceAll("XC", "d");
        roman = roman.replaceAll("CD", "e");
        roman = roman.replaceAll("CM", "f");
        int romanToInt = 0;
        for (int i = 0; i < roman.length(); i++) {
            romanToInt += getIntByRomanChar(roman.charAt(i));
        }
        return romanToInt;
    }

    private static int getIntByRomanChar(char c) {
        switch (c) {
            case 'a':
                return 4;
            case 'b':
                return 9;
            case 'c':
                return 40;
            case 'd':
                return 90;
            case 'e':
                return 400;
            case 'f':
                return 900;
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }
}
