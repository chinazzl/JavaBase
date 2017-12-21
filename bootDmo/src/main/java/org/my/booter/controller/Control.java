package org.my.booter.controller;

import org.my.booter.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Felix on 2017/7/7.
 * 进行json接口开发
 */
@Controller
@RequestMapping("/mvc")
public class Control {

    /**
     * Json接口
     * @return
     */
    @RequestMapping("/home")
    @ResponseBody
    public Student initer(){
        Student student = new Student();
        student.setStuName("zs");
        student.setStuAge("156");
        student.setStuSex("male");
        System.out.println("Age: "+student.getStuAge());
        return  student;
    }


}
