package org.my.booter.controller;

import org.my.booter.dao.StuRepostiry;
import org.my.booter.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Felix on 2017/7/7.
 * 进行json接口开发
 */
@Controller
@RequestMapping("/mvc")
public class Control {

    @Autowired
    private StuRepostiry stuRepostiry;
    /**
     * Json接口
     * @return
     */
//    @RequestMapping("/home")
//    @ResponseBody
//    public Student initer(){
//        Student student = new Student();
//        student.setStuName("zs");
//        student.setStuAge("156");
//        student.setStuSex("male");
//        System.out.println("Age: "+student.getStuAge());
//        return  student;
//    }

    /**
     * 实现图片上传
     */
//    @RequestMapping("/upload")
//        public String upLoadImage(@RequestParam("upload")MultipartFile file, HttpServletRequest request){
//        String originalFilename = file.getOriginalFilename();
//        String flag = stuRepostiry.uploadImage(originalFilename);
//        return "success";
//    }


}
