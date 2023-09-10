package com.wzh.controller;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.User;
import com.wzh.service.UserService;
import com.wzh.until.JsonObject;
import com.wzh.until.PageRet;
import com.wzh.until.Time;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/msave")
    @ResponseBody
    public JsonObject msave(@RequestParam("username") String username,
                            User user) {
        User u = userService.getByUsername(username);
        System.out.println(u);
        if (u == null) {
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setTime(Time.getTime());
            userService.save(user);
            System.out.println("添加数据");
            return new JsonObject("200", "", "");
        }
        else {
            return new JsonObject("500", "账号已存在", "");
        }
    }
    @RequestMapping("/login")
    @ResponseBody
    public JsonObject login(@RequestParam("username") String username,
                            @RequestParam("pass") String pass,
                            @RequestParam("type") String type
    ) {
        System.out.println("账号："+username + "密码：" +pass);
        User u = userService.getByUsernametype(username,type);
        System.out.println(u != null);
        if (u == null) {
            System.out.println("账号不存在");
            return new JsonObject("500", "账号不存在", "");
        } else if (u.getPass().equals(pass) && u.getUsername().equals(username)) {
            System.out.println("登录成功");
            return new JsonObject("200", "", JSON.toJSONString(u));
        } else {
            System.out.println("密码错误");
            return new JsonObject("300", "密码错误", "");
        }
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public JsonObject findAll(User user){
        List<User> list = userService.list(user);
        System.out.println(list);
        try{
            return new JsonObject("200","", JSONUtil.toJsonStr(list));
        }
        catch (Exception e){
            System.out.println(e.toString());
            return new JsonObject("500","失败","");
        }
    }
    @RequestMapping("/tolist")
    public String tolist() {
        return "User/hzlist";

    }
    @RequestMapping("/tolistys")
    public String tolistys() {
        return "User/yslist";

    }
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String name){
        // 顾名思义 实体和数据 同时返回页面模板和数据
        Page<User> pageInfo = PageHelper.startPage(page, limit, "time desc");
        List<User> list = userService.listAll(name);
        PageInfo<User> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping(value = "/pagegetallys")
    @ResponseBody
    public PageRet pagegetallys(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String name){
        // 顾名思义 实体和数据 同时返回页面模板和数据
        Page<User> pageInfo = PageHelper.startPage(page, limit, "time desc");
        List<User> list = userService.listys(name);
        PageInfo<User> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/toaddys")
    public String toaddys(Model model) {
        model.addAttribute("id", "");
        System.out.println("打开添加界面");
        return "User/add";
    }
    @RequestMapping("/toadd")
    public String toadd(Model model) {
        model.addAttribute("id", "");
        System.out.println("打开添加界面");
        return "User/hzadd";
    }
    @RequestMapping("/del")
    @ResponseBody
    public PageRet del(@RequestParam("id") String ids[]){
        for (String id : ids) {
            userService.del(id);
        }
        return new PageRet(200, "", null, 0);
    }
    @RequestMapping("/toget")
    public String toget(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        System.out.println("修改界面");
        return "User/hzadd";
    }
    @RequestMapping("/togetys")
    public String togetys(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        System.out.println("修改界面");
        return "User/add";
    }
    @RequestMapping("/get")
    @ResponseBody
    public JsonObject get(@RequestParam("id") String id) {
        User u = userService.get(id);
        System.out.println(u);
        System.out.println("获取值");
        return new JsonObject("200", "", u);
    }
    //    更新
    @RequestMapping("/update")
    @ResponseBody
    public PageRet update(User u) {
        userService.update(u);
        System.out.println("更新数据");
        return new PageRet(200, "", null, 0);
    }
}
