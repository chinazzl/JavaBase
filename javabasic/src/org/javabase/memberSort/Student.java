package org.javabase.memberSort;

public class Student extends Person {

    private static String names = "student static name";

    private String age;
    static {
        System.out.println("student 子类静态代码块");
    }

    public Student(){
        System.out.println("子类的无参构造");
    }

    public Student(String age){
        System.out.println("子类的有参构造");
    }
}
