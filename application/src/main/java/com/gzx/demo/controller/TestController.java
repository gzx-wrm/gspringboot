package com.gzx.demo.controller;


import com.gzx.mvc.annotation.RequestMapping;
import com.gzx.mvc.annotation.RestController;
import com.gzx.spring.annotation.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
// spring 实现了独特的组合注解特性，即注解之间实现了继承、注解内的属性可覆盖
// 这里没有实现，所以需要额外打一个Component注解
@Component
@RequestMapping("/")
public class TestController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return "hello springboot";
    }
}
