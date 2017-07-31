package org.my.booter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Felix on 2017/7/7.
 */
@Controller
public class Control {

    @RequestMapping("/home")
    @ResponseBody
    public String initer(){
        return "hello";
    }
}
