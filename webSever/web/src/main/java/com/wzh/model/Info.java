package com.wzh.model;

import java.util.Date;

public class Info {
    private String id;

    private String uid;

    private String tid;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    private String jid;

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", tid='" + tid + '\'' +
                ", wid='" + wid + '\'' +
                ", date='" + date + '\'' +
                ", nickname='" + nickname + '\'' +
                ", js='" + js + '\'' +
                ", quant='" + quant + '\'' +
                ", msg='" + msg + '\'' +
                ", state='" + state + '\'' +
                ", time='" + time + '\'' +
                ", tname='" + tname + '\'' +
                ", yroom='" + yroom + '\'' +
                '}';
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    private String wid;

    private String date;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    private String js;
    private String quant;

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String time;

    private String tname;

    private String yroom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getYroom() {
        return yroom;
    }

    public void setYroom(String yroom) {
        this.yroom = yroom;
    }
}
