package com.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//测试异常的类  ------   适用于 非 ajax的。。
@Controller
@RequestMapping("/textException")
public class TextExceptionController {  // /textException/test01

    @RequestMapping("/test01")
    public String test01(){
        int i = 6/0;
        return "loginVue";
    }
}
