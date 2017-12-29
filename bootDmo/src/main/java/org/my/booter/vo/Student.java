package org.my.booter.vo;

import javax.persistence.*;

/**
 * Created by Felix on 2017/12/19.
 */
@Entity
@Table(name = "stu")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    @Column(nullable = true,name = "sname")
    protected String stuName ;
    @Column(nullable = true,name = "sage")
    private String stuAge;
    @Column(nullable = true,name = "ssex")
    private String stuSex;
    @Column(nullable = true,name = "time")
    private String time;
    @Column(nullable = true,name = "endtime")
    private String endTime;
    @Column(nullable = true,name = "email")
    private String email;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }
}
