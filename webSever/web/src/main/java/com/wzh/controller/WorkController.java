package com.wzh.controller;


import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.model.Persion;
import com.wzh.model.User;
import com.wzh.model.Work;
import com.wzh.service.UserService;
import com.wzh.service.WorkService;
import com.wzh.until.JsonObject;
import com.wzh.until.PageRet;
import com.wzh.until.Time;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/work")
public class WorkController {

    @Resource
    WorkService workService;

    @Resource
    UserService userService;
    @RequestMapping("/tolist")
    public String tolist(Model model) {
        List<User> list = userService.ys();
        model.addAttribute("dlist",list);
        return "Work/list";

    }
    @RequestMapping(value = "/pagegetall")
    @ResponseBody
    public PageRet list(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                        @RequestParam("name") String name){
        // 顾名思义 实体和数据 同时返回页面模板和数据
        Page<Work> pageInfo = PageHelper.startPage(page, limit, "time desc");
        List<Work> list = workService.listAll(name);
        PageInfo<Work> all = new PageInfo<>(list);
        return new PageRet(0, "", all.getList(), (int) all.getTotal());
    }
    @RequestMapping("/toadd")
    public String toadd(Model model) {
        List<User> list = userService.ys();
        model.addAttribute("id", "");
        model.addAttribute("dlist",list);
        System.out.println("打开添加界面");
        return "Work/add";
    }
    @RequestMapping("/del")
    @ResponseBody
    public PageRet del(@RequestParam("id") String ids[]){
        for (String id : ids) {
            workService.del(id);
        }
        return new PageRet(200, "", null, 0);
    }
    @RequestMapping("/toget")
    public String toget(@RequestParam("id") String id, Model model) {
        List<User> list = userService.ys();
        model.addAttribute("dlist",list);
        model.addAttribute("id", id);
        System.out.println("修改界面");
        return "Work/add";
    }
    @RequestMapping("/add")
    @ResponseBody
    public PageRet save(Work work) {
        Work work1 = workService.gete(work);
        if (work1 == null){
            work.setId(UUID.randomUUID().toString().replace("-", ""));
            work.setTime(Time.getTime());
            workService.save(work);
            System.out.println("添加数据");
            return new PageRet(200, "", null, 0);
        }
        else {
            return new PageRet(500, "该医生在该时间段已排班", null, 0);

        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public PageRet update(Work work) {
        workService.update(work);
        System.out.println("更新数据");
        return new PageRet(200, "", null, 0);
    }
    @RequestMapping("/get")
    @ResponseBody
    public JsonObject get(@RequestParam("id") String id) {
        Work work = workService.get(id);
        System.out.println(work);
        System.out.println("获取值");
        return new JsonObject("200", "", work);
    }
    @RequestMapping("/getwork")
    @ResponseBody
    public JsonObject getwork(@RequestParam("quant") String quant,@RequestParam("date") String date,@RequestParam("uid") String uid) {
        Work work = workService.selectkey(quant, date,uid);
        if (work ==null){
            return new JsonObject("500", "","");
        }
        else {
            return new JsonObject("200", "",JSONUtil.toJsonStr(work)  );

        }
    }
    @RequestMapping("/week")
    @ResponseBody
    public JsonObject week() {

        // 获取当天的毫秒时间
        long currentTimeMillis = System.currentTimeMillis();

        List<String> list = getAllWeekDayDateByMillis(currentTimeMillis);
        List<String> all_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            all_list.add(list.get(i));
            System.out.println("list.get(i)"+list.get(i));
        }
        for(String date:list) {

            System.out.println("date : "+date);
        }
        return new JsonObject("200", "", JSONUtil.toJsonStr(all_list) );
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public JsonObject findAll(@RequestParam("type") String type,@RequestParam("quant") String quant,@RequestParam("nickname") String nickname,@RequestParam("post") String post){
        if (quant.equals("上午")){
            List<Work> list = workService.yy(type,nickname,post);
            return new JsonObject("200","", JSONUtil.toJsonStr(list));
        }
        else if (quant.equals("下午")){
            List<Work> list = workService.yyzw(type,nickname,post);
            return new JsonObject("200","", JSONUtil.toJsonStr(list));
        }
        else {
            List<Work> list = workService.yyws(type,nickname,post);
            return new JsonObject("200","", JSONUtil.toJsonStr(list));
        }
    }

    // 获取指定日期毫秒时间的当周一星期的日期
    private static List<String> getAllWeekDayDateByMillis(long timeMills){
        List<String> list = new ArrayList<String>();

        // 得到指定时间是周几
        String week = getWhatDaySomeday(timeMills);
        System.out.println("日期是：" + week);

        // 记录与周一的间隔天数
        int dayFromMonday=getHowManyDayFromMonday(week);
        System.out.println("dayFromMonday : "+ dayFromMonday);

        // 获取这周第一天毫秒值
        long dayMillis = 24*60*60*1000;
        // 获取这周第一天的日子
        long firstOfWeekMillis = timeMills - dayFromMonday * dayMillis;

        // 使用 for 循环得到当前一周的日子（7天的日子）
        for(long i = firstOfWeekMillis;i<firstOfWeekMillis+ 7 *dayMillis;i+=dayMillis) {
            Date targetDate = new Date(i);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String targetDay = format.format(targetDate);
            list.add(targetDay);
        }

        return list;
    }
    private static String getWhatDaySomeday(long timeMillis) {

        Date toDayDate = new Date(timeMillis);
        SimpleDateFormat formatE = new SimpleDateFormat("E");
        String week =null;
        try {
            week = formatE.format(toDayDate);
            System.out.println(week);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return week;
    }

    // 根据 星期几到获取与 星期一 相差几天
    private static int getHowManyDayFromMonday(String someDay) {
        int day = 0;
        switch(someDay) {
            case "星期一":
                day=0;
                break;
            case "星期二":
                day=1;
                break;
            case "星期三":
                day=2;
                break;
            case "星期四":
                day=3;
                break;
            case "星期五":
                day=4;
                break;
            case "星期六":
                day=5;
                break;
            case "星期日":
                day=6;
                break;
            default:
                System.out.println("不存在这样的星期几 : "+ someDay);
                break;
        }

        return day;
    }
}
