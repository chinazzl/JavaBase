package leetCode;

import java.util.*;

/**
 * 给定一个数字 N，当它满足以下条件的时候返回 true：
 * <p>
 * 把原数字旋转180°以后得到新的数字。
 * <p>
 * 如 0, 1, 6, 8, 9 旋转 180° 以后，得到了新的数字 0, 1, 9, 8, 6 。
 * <p>
 * 2, 3, 4, 5, 7 旋转 180° 后,得到的不是数字。
 * <p>
 * 易混淆数字 (confusing number) 就是一个数字旋转180°以后，得到和原来不同的数字，且新数字的每一位都是有效的。
 */
public class MixNum {
    public static void main(String[] args) {
        Boolean confusingNumber = isConfusingNumber(88);
        System.out.println(confusingNumber);
    }

    private static Boolean isConfusingNumber(Integer n) {
        int v = 0;int nn = n;
        while(n>0){
            int d =n%10;
            if(d==2||d==3||d==4||d==5||d==7){
                return false;
            }
            int x = d;
            if(d==9){
                x = 6;
            }else if(d==6){
                x = 9;
            }
            v = 10*v+x;

            n /=10;
        }
        // System.out.println(v);
        return v!=nn;
//        List targetList = new ArrayList<>();
//        targetList.add(0);
//        targetList.add(1);
//        targetList.add(6);
//        targetList.add(8);
//        targetList.add(9);
//
//        int len = String.valueOf(num).length(); //返回当前值的长度
//        String[] strs = new String[len];
//        if (len == 1) {
//            return targetList.contains(num);
//        } else {
//            int j = 0;
//            int mod = 0;
//            int cs = 0;
//            int temp = 0;
//            for (int i = (int) Math.pow(10, len - 1); i >= 10; i = i / 10) {
//                cs = cs == 0 ? num / i : temp / i;
//                temp = temp == 0 ? num % i : temp % i;
//                mod = cs;
//                strs[j] = mod + "";
//                if (i == 10) {
//                    strs[j + 1] = temp + "";
//                } else {
//                    ++j;
//                }
//            }
//            Set rs = new HashSet();
//            for (int i = 0; i < strs.length; i++) {
//                System.out.print(strs[i] + ",");
//                rs.add(Integer.parseInt(String.valueOf(strs[i])));
//            }
//
//            int totalCount = 0;
//
//
//
//        }

    }
}
