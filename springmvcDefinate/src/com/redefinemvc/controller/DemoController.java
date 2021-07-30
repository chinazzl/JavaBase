package com.redefinemvc.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/4/29 16:10
 * @Modified By：
 */
public class DemoController extends AbstractController {


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String attribute = (String)getServletContext().getAttribute("mydata");
        System.out.println(attribute);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        ModelAndView mv = new ModelAndView("userlist","ints",list);
        return mv;
    }
}
