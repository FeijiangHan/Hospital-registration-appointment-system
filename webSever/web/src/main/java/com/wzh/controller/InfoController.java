package com.wzh.controller;


import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.Info;
import com.wzh.model.Persion;
import com.wzh.model.User;
import com.wzh.service.InfoService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/info")
public class InfoController {

    @Resource
    InfoService infoService;

    @Resource
    UserService userService;
    @RequestMapping("/tolist")
    public String tolist(@RequestParam("tid") String tid, Model model) {
        model.addAttribute("tid",tid);
        System.out.println("查询评论表"+tid);
        return "Info/list";
    }
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("tid") String tid) {
        // 顾名思义 实体和数据 同时返回页面模板和数据
        Page<Info> pageInfo = PageHelper.startPage(page, limit, "id desc");
        List<Info> list = infoService.selectwid(tid);
        List<User> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            User user = userService.get(list.get(i).getUid());
            list1.add(user);
        }
        PageInfo<User> all = new PageInfo<>(list1);
        System.out.println(tid);
        System.err.println("list"+list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/msave")
    @ResponseBody
    public JsonObject msave(Info info) {
        Info info1 = infoService.selectinfo(info.getJid(),info.getQuant());
        if (info1==null){
            info.setId(UUID.randomUUID().toString().replace("-", ""));
            info.setTime(Time.getTime());
            info.setDate(Time.getDate());
            info.setState("正常");
            infoService.save(info);
            System.out.println("添加数据");
            return new JsonObject("200", "", "");
        }
        else {
            return new JsonObject("500", "该时间段已预约", "");

        }


    }

    @RequestMapping("/findMy")
    @ResponseBody
    public JsonObject findMy(@RequestParam("uid") String uid) {
        List<Info> list = infoService.listMy(uid);
        List<User> all_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            User u  = userService.get(list.get(i).getTid());
            u.setDate(list.get(i).getDate());
            u.setState(list.get(i).getState());
            u.setPid(list.get(i).getId());
            all_list.add(u);
        }
        return new JsonObject("200", "", JSONUtil.toJsonStr(all_list));
    }
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(Info info) {
        infoService.update(info);
        return new JsonObject("200", "", "");
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public JsonObject findAll(@RequestParam("uid") String uid) {
        List<Info> list = infoService.listMyAll(uid);
        List<User> all_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            User u  = userService.get(list.get(i).getTid());
            u.setDate(list.get(i).getDate());
            u.setState(list.get(i).getState());
            u.setPid(list.get(i).getId());
            all_list.add(u);
        }
        return new JsonObject("200", "", JSONUtil.toJsonStr(all_list));
    }
    @RequestMapping("/WeekAll")
    @ResponseBody
    public JsonObject WeekAll(@RequestParam("uid") String uid) {
        Info in = infoService.selectweek(uid);
        return new JsonObject("200", "", in.getJs());
    }
    @RequestMapping("/infoMy")
    @ResponseBody
    public JsonObject infoMy(@RequestParam("uid") String uid) {
        List<Info> list = infoService.selectrc(uid);
        return new JsonObject("200", "", JSONUtil.toJsonStr(list));
    }
    @RequestMapping("/getTaday")
    @ResponseBody
    public JsonObject getTaday(@RequestParam("tid") String tid) {
        List<Info> list = infoService.selectday(tid);
        return new JsonObject("200", "", JSONUtil.toJsonStr(list));
    }
}
