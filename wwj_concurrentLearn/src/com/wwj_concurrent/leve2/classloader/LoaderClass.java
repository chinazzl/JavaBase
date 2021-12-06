package com.wwj_concurrent.leve2.classloader;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月30日 21:47
 * @Description
 **********************************/
public class LoaderClass {

    public static void main(String[] args) {
        String string = new String("zs");
        PrepareClassLoader prepareClassLoader = new PrepareClassLoader();
        System.out.println(prepareClassLoader.getClass().getClassLoader().getParent());
        System.out.println(string.getClass().getClassLoader());
    }
}
