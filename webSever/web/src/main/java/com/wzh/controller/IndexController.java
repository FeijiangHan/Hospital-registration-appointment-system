package com.wzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping(value = "/login") // 访问路径
    public ModelAndView toIndex() {

        // 返回templates目录下index.html
        ModelAndView view = new ModelAndView("login");
        return view;
    }
    @RequestMapping("/main")
    public ModelAndView tomain() {
        // 返回templates目录下main.html
        ModelAndView view = new ModelAndView("main");
        return view;
    }
}
