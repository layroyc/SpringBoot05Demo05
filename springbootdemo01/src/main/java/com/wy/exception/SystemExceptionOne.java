package com.wy.exception;

import com.wy.respcode.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//controller的通知，利用aop动态的织入异常信息
@ControllerAdvice
public class SystemExceptionOne {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    //把异常发给前端
    public Result showException(Exception e){
        System.out.println("e = " + e);
      return new Result<>(50001,"系统忙。。。。。");
    }

    //给校验处理他的统一异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result showMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String[] msgs = new String[allErrors.size()];
        int i = 0;
        for (ObjectError allError : allErrors) {
            msgs[i] = allError.getDefaultMessage();
            i++;
        }
        return new Result(50004,msgs[0]);
    }

    //作业1：给自己的boot项目做出中文的接口doc文档
    //作业2：给学生增加做出后端的参数校验


}
