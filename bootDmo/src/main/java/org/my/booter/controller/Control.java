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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    @RequestMapping("/home")
    @ResponseBody
    public Student initer(HttpServletRequest request){
        Student student = new Student();
        HttpSession session = request.getSession();
        String id = session.getId();
        System.out.println(id);
        return  student;
    }

    /**
     * cookie
     */
    @RequestMapping("/coo")
    public void opCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("me","hello");
        cookie.setPath("/home/");
        response.addCookie(cookie);
    }

    /**
     * 实现图片上传
     */
    @RequestMapping("/upload")
        public void upLoadImage(@RequestParam("upload")MultipartFile file, HttpServletRequest request,HttpServletResponse response){
        String originalFilename = file.getOriginalFilename();
        String realPath = request.getRealPath("/");
        String contextPath = request.getContextPath();
        System.out.println("-------"+realPath.equals(contextPath));
        try{
            stuRepostiry.uploadImage(originalFilename);
            response.sendRedirect("/jsp/hello.jsp");
        }catch (Exception e){

        }

//        return "success";
    }


}
