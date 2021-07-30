package number;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/3/25 8:40
 * @Modified By：
 */
public class ReadLogAndHandler {
    private static final Integer COL_NUMBER = 4;

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\IdeaProjects\\JavaBase\\javabasic\\resources\\mylog.log");
        BufferedReader bfr = new BufferedReader(new FileReader(file));
        char[] buffer = new char[1024];
        String len;
        List<String[]> temp = new ArrayList<>();
        List<String> temp_2 = new ArrayList<>();
        int tmpLine = 0;
        try {
            while ((len = bfr.readLine()) != null) {
                String s = new String(len.getBytes(), "utf-8");
                //把每一行存入一个集合中 第一版
                /*temp.add(s.split(","));
                System.out.println(s);*/
                temp_2.add(s);
                System.out.println(s);
            }
//            Map<String, Integer> num = getNum(temp);
            Map<String, Integer> num = getNumVersion2(temp_2);
            System.out.println("lineNo: " + num.get("lineNo"));
        } finally {
            bfr.close();
        }
    }

    private static Map<String, Integer> getNum(List<String[]> list) {
        Map<String, Integer> result = new HashMap<>();
        //行数
        int len = list.size();
        for (int i = 0; i < COL_NUMBER; i++) {
            //每一列的数字之和
            int col_result = 0;
            for (int j = 0; j < len; j++) {
                //获取每一行的数据集
                String[] strs = list.get(j);
                if (strs != null) {
                    col_result += Integer.parseInt(strs[i]);
                }
            }
            System.out.println("第 " + (i + 1) + "列的 和为：" + col_result);
        }
        result.put("lineNo", len);
        return result;
    }

    private static Map<String, Integer> getNumVersion2(List<String> list) {
        Map<String, Integer> resultMap = new HashMap<>();
        //行数
        int len = list.size();
        int temp = 0;
        for (int i = 0, k = 0; i < COL_NUMBER; i++, temp = ++k * 2) {
            //每一列的数字之和
            int col_result = 0;
            for (int j = 0; j < len; j++) {
                //获取每一行的数据集
                String str = list.get(j);
                if (temp < str.length() && temp % 2 == 0) {
                    col_result += Integer.parseInt(String.valueOf(str.charAt(temp)));
                }
            }
            System.out.println("第 " + (i + 1) + "列的 和为：" + col_result);
        }
        resultMap.put("lineNo", len);

        return resultMap;
    }

}
