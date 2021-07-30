package org.javabase.serialization;

import java.io.*;

/**
 * SerializationDemo 结合SerializableDemo2说明 一个类要想被序列化必须实现Serializable接口
 */
public class SerializationDemo {
    public static void main(String[] args) {

        User u = new User();
        u.setName("hehe");
        u.setAge(23);
        System.out.println(u);

        try (FileOutputStream fos = new FileOutputStream("tempFile");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("tempFile");
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file)
        )){
          User userNew = (User) ois.readObject();
            System.out.println(userNew);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
