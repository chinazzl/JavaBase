package org.javabase.reflect;

import java.io.InputStream;

/**
 * Created by Felix on 2017/8/4.
 * 配置文件反射加载
 * - 类加载器加载只读配置文件

 `类名.class.getClassLoader().getResourceAsStream(str);`

 - 类名.class.getResourceAsStream(str),实质还是调用类加载器。

 */
public class PropertiesLoader {
    public static void main(String[] args) throws Exception {
        String str = "/org/javabase/reflect/config.properties";
        InputStream resourceAsStream = PropertiesLoader.class.getClass().getResourceAsStream(str);
        byte[] ch = new byte[1024];
        int len = 0;
        while ((len = resourceAsStream.read(ch))!= -1){
            System.out.println(new String(ch,0,len));
        }
    }
}
