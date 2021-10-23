package com.wy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wy.bean.Student;
import com.wy.bean.StudentExample;
import com.wy.respcode.Result;
import com.wy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //使用 @ExceptionHandler 处理异常， 该注解的作用域，仅仅作用于 该类！ 需要该注解 在这个 controller中
    @ExceptionHandler(ArithmeticException.class)
    public Result studentExcetion(ArithmeticException e){
        System.out.println("e = " + e);
        return new Result(5001,"系统繁忙，请稍后");
    }
    //全查  注意 不用 map  公司中 都用 一个类， 好几个类组成的类， 叫做 统一响应类， 每个公司都不一样
    @RequestMapping("/selectAll")    //   /student/selectAll
    public Result selectALL(){

        List<Student> students = studentService.selectByExample(null);
        //Result result = new Result(students);
        //return result;  或

        return new Result(students);//匿名内部类
    }
    //分页查询   带参数的+分页查询
    @RequestMapping("/selectPageAll")
    //value = "page"  前端传的参数
    public Result selectPageAll(Student student ,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                               @RequestParam(value = "limit",defaultValue = "6",required = true) Integer limit){
        System.out.println("student = " + student);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();

        int i = 9/0;
        //使用 pageHelper分页
        PageHelper.startPage(page,limit);//这个PageHelper 拿到前端传来的参数

        if(null != student.getStudentSex()&&!"".equals(student.getStudentSex())){
            criteria.andStudentSexEqualTo(student.getStudentSex());
        }
        if(null != student.getStudentName()&&!"".equals(student.getStudentName())){
            criteria.andStudentNameEqualTo(student.getStudentName());
        }

        List<Student> students = studentService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(students);

        return new Result(pageInfo);
    }

    //分页查询   带参数的+分页查询
    @RequestMapping("/selectAllByPage")
    public Result selectAllByPage(){
        return null;
    }

    //修改
    @RequestMapping("/updateOne")
    public Result updateOne(@RequestBody Student student){
        System.out.println("student = " + student);
        int i = studentService.updateByPrimaryKeySelective(student);
        if(i == 1){
            return new Result();
        }else{
            return new Result(40001,"修改失败");
        }
    }
}
