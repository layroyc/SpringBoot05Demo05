package com.wy.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//异常解析器
@Component
public class SystemException implements HandlerExceptionResolver {
    //返回一个modelandview，注意此项不太适合前后端分离
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //定义一个错误的视图！！
        System.out.println("ex = " + ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/systemError");
        return modelAndView;
    }
}
