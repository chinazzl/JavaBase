package ioLearn;

import java.io.*;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/4/27 22:29
 * @Modified By：
 * 练习 File
 */
public class FileDemo {

    public static void main(String[] args) throws IOException {
        String info = OutOfFile("javabasic/resources/ReadMe.md");
        writeFile("javabasic/resources/","new.md",info);
    }

    /**
     * 读 文件中的数据
     *
     * @param path
     * @throws IOException
     */
    private static String OutOfFile(String path) throws IOException {
        File file = new File(path);
        String out = null;
        try (FileInputStream fis = new FileInputStream(file);) {
            byte[] bytes = new byte[1024];
            int len = 0;

            while ((len = fis.read(bytes)) != -1) {
                out = new String(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * 写入文件
     *
     * @param path     写入文件路径
     * @param fileName 写入文件名称
     */
    public static void writeFile(String path, String fileName, String info) throws IOException {
        File file = new File(path + "/" + fileName);
        file.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {

            byte[] bytes = info.getBytes();
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
