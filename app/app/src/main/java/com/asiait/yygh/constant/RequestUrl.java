package com.asiait.yygh.constant;

/**
 * 请求接口地址
 *
 */
public class RequestUrl {


    /**
     *  请求根地址
     */
    public static String baseUrl="http://192.168.31.222:8088/";

    public static String TestUrl="";
//    登录
    public static String Userlogin=baseUrl+"user/login";

//上传图片
    public static String imageupload = baseUrl+"upload/imageqd";
//用户个人信息更新
    public static String Userupdate= baseUrl+"user/grxx";
//密码更新
    public static String Passupdate= baseUrl+"user/updatepass";

    public static String Usermsave= baseUrl+"user/msave";
    public static String userGetALl= baseUrl+"user/findAll";
    public static String infoadd= baseUrl+"info/msave";
    public static String persionadd= baseUrl+"persion/msave";
    public static String persionGetALl= baseUrl+"persion/findAll";
    public static String persionUpdate= baseUrl+"persion/update";
    public static String Jzrdel= baseUrl+"persion/del";
    public static String infoGetMy=baseUrl+"info/findMy";
    public static String infoupdate=baseUrl+"info/update";
    public static String infoGetMyAll=baseUrl+"info/findAll";
    public static String infoweek=baseUrl+"info/WeekAll";
    public static String infoGetMy2=baseUrl+"info/infoMy";
    public static String infoday=baseUrl+"info/getTaday";
    public static String getdate=baseUrl+"work/week";
    public static String getwork=baseUrl+"work/getwork";
    public static String UsermUpdate=baseUrl+"user/update";
    public static String workGetALl=baseUrl+"work/findAll";
}
