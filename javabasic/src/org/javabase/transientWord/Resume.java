package org.javabase.transientWord;

import java.io.Serializable;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/13 16:49
 * @Modified By：
 */
public class Resume implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private static String sex;
    transient private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void dispaly() {
        System.out.println("姓名：" + name + "\t年龄：" + age + "\t性别：" + sex);
//        System.out.println("工作经历："+workExperience.getWorkDate()+"\t"+workExperience.getCompany());
    }

    public Object clone() {
        try {
            Resume resume = (Resume) super.clone();
            return resume;
        } catch (Exception e) {
            return null;
        }

    }
}
