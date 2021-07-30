package com.learnSlf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/20 23:29
 * @Modified By：
 */
public class SlfTest {
    private static final  Logger logger = LoggerFactory.getLogger(SlfTest.class);

    @Test
    public void test_1(){
        logger.error("error");
        logger.info("info");
        try {
            int i = 1/0;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.warn("致命错误",e);
        }
    }

    @Test
    public void test2() {
        String str = "a;b;c;d;e";
        String result = null;
        if(str != null) {
            StringBuilder sb = new StringBuilder();
            String[] strArr= str.split(";");
            for (int i = 0; i < strArr.length; i++) {
                sb.append("'" + strArr[i] + "'").append(",");
            }
            result = sb.substring(0,sb.length()-1);
        }
        System.out.println(result);
    }
}
