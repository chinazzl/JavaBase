package leetCode.review;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
//        int[] i = {6,9};
//        List targetList = new ArrayList<>();
//        targetList.add(0);
//        targetList.add(1);
//        targetList.add(6);
//        targetList.add(8);
//        targetList.add(9);
//        System.out.println(targetList.contains(i));
        String ipReg = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern ipPattern = Pattern.compile(ipReg);

        boolean flag = ipPattern.matcher("192.168.0").matches();
        System.out.println(flag);
        flag = ipPattern.matcher("aaaaaa").matches();
        System.out.println(flag);
    }


}
