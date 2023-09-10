package com.wzh.controller;

import com.wzh.model.Manager;
import com.wzh.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    ManagerService managerService;
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("pass") String pass,
                        Model model,
                        HttpSession session) {
        Manager m = managerService.getByUsername(username);
        System.out.println("登录");
        if (m == null) {
            System.out.println("账号不存在");
            model.addAttribute("msg","账号不存在");
            return "/login";
        }
        else if (m.getPass().equals(pass) && m.getUsername().equals(username)){
            //往session中存入你想要的东西
            session.setAttribute("loginSuccess", m.getUsername());
            return "redirect:/index.html";
        } else {
            model.addAttribute("msg","密码不正确");
            System.out.println("密码不正确");
            return "/login";
        }
    }
    @RequestMapping("/exit")
    public ModelAndView toetit( HttpSession session,
                                HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/login");
        session.removeAttribute("loginSuccess");
        Object loginSuccess = request.getSession().getAttribute("loginSuccess");
        System.out.println("退出"+loginSuccess);
        return view;
    }

}
