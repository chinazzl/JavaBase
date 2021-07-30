package org.javabase.transientWord;

import java.io.*;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/13 17:01
 * @Modified By：
 * 1，一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 *
 * 2，transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户
 *    自定义类变量，则该类需要实现Serializable接口。
 *
 * 3，一个静态变量不管是否被transient修饰，均不能被序列化。
 */
public class TransientTest {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        //序列化 Resume
//        Resume resume = new Resume();
//        resume.setAge("20");
//        resume.setName("aa");
//        resume.setSex("male");
//        resume.dispaly();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("resume.txt"));
//        objectOutputStream.writeObject(resume);
//        objectOutputStream.close();

        //反序列化Resume
        Deserialization();
    }

    private static void Deserialization() throws IOException, ClassNotFoundException {
        Resume resume = new Resume();
        resume.setSex("female");
        //反序列化
        ObjectInputStream inputStream  = new ObjectInputStream(new FileInputStream("resume.txt"));
        Resume resume1 = (Resume) inputStream.readObject();
        System.out.println(resume.getSex().intern() == resume1.getSex());
        resume1.dispaly();
    }
}
