package com.wy.controller;

import com.wy.respcode.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/textExceptionOne")
public class TextExceptionOneController {

    @RequestMapping("/testone")    //   /textExceptionOne/testone
    public Result testone(){
        int i = 8/0;
        return new Result();
    }
}
