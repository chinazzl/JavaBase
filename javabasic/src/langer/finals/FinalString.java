package langer.finals;

import java.util.Arrays;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/15 10:34
 * @Modified By：
 */
public class FinalString {
    public static void main(String[] args) {
//        final String a= "adf" +
//                " ads";
//        String b = a;
//        String s = b.replaceAll("a", "v");
//        System.out.println(s);
        String a = "orgNo#appNo#statYm#actCode#consNo#consName#consSortCode#orderNo#stealTypeCode#errorid#topiccode#";
        System.out.println(a.indexOf("appNo"));
        String[] os = a.split("#");
        int i = Arrays.asList(os).indexOf("appNo");
        System.out.println(i);
        for (String s : os){
            System.out.print(s + ",");
        }
    }
}
