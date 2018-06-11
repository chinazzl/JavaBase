package org.my.booter.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class imageUtils {

    /**
     * 图片复制到服务器
     */
    public String uploadUtil(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] bytes = new byte[fis.available()];
            byte[] buffer = new byte[1024 * 1024];
            int b = fis.read(buffer);
            long millis = System.currentTimeMillis();
            double random = Math.random();
            String name  = String.valueOf(millis) + String.valueOf(Double.valueOf(random))+".jpg";
            FileOutputStream fos = new FileOutputStream("D:\\360Downloads\\apache-tomcat-8.5.15\\webapps\\upload\\"+name+"");
            while (b != -1){
                fos.write(buffer,0,1);
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

        return "success";
    }
}
