package com.wzh.controller;


import cn.hutool.json.JSONUtil;
import com.wzh.model.Info;
import com.wzh.model.Persion;
import com.wzh.model.User;
import com.wzh.service.PersionService;
import com.wzh.until.JsonObject;
import com.wzh.until.PageRet;
import com.wzh.until.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/persion")
public class PersionController {
    @Resource
    PersionService persionService;

    @RequestMapping("/msave")
    @ResponseBody
    public JsonObject msave(Persion persion) {
        persion.setId(UUID.randomUUID().toString().replace("-", ""));
        persion.setTime(Time.getTime());
        persionService.save(persion);
        System.out.println("添加数据");
        return new JsonObject("200", "", "");
    }
    //    更新
    @RequestMapping("/update")
    @ResponseBody
    public JsonObject update(Persion p) {
        persionService.update(p);
        return new JsonObject("200", "", "");
    }
    @RequestMapping("/del")
    @ResponseBody
    public JsonObject del(@RequestParam("id") String id){
        persionService.del(id);
        return new JsonObject("200", "", "");
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public JsonObject findAll(@RequestParam("uid") String uid){
        List<Persion> list = persionService.getMy(uid);
        System.out.println(list);
        try{
            return new JsonObject("200","", JSONUtil.toJsonStr(list));

        }
        catch (Exception e){
            System.out.println(e.toString());
            return new JsonObject("500","失败","");
        }
    }
}
