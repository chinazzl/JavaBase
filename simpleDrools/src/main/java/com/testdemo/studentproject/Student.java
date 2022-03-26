package com.testdemo.studentproject;

import java.io.Serializable;

/**
 * @author Julyan
 * @version V1.0
 * @Description: 通过workbench 扫描到规则使用的实体类。路径必须相同！
 * @Date: 2022/3/18 17:02
 */
public class Student implements Serializable {
    private int id;

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
