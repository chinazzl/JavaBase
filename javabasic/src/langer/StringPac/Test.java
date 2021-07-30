package langer.StringPac;

import java.lang.reflect.Field;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/1 17:35
 * @Modified By：
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        //创建一个class对象
        Class c=Student.class;
        //创建一个Student对象
        Student stu=(Student) c.newInstance();
        //获取其中的public修饰的Filed对象
        Field f1=c.getField("gender");
        //赋值
//        f1.set(stu,"真白痴");
//        //通过f1对象获取gender的值
//        String gender=(String) f1.get("gender");
//        System.out.println("性别:"+gender);

//        Field f2=c.getField("age");
//        f2.set(stu, 30);
//        int age=f2.getInt("age");
//        System.out.println("年龄:"+age);
        //获取public修饰的属性
        Field[] f3=c.getFields();
        for (Field field1 : f3) {
            System.out.println(field1);
        }
        //获取所有的属性,包括private修饰的方法
        Field[] f4=c.getDeclaredFields();
        for (Field field2 : f4) {
            System.out.println(field2);
        }
    }
}
