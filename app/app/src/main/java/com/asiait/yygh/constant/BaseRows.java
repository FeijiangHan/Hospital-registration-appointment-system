package com.asiait.yygh.constant;

import java.io.Serializable;

public class BaseRows implements Serializable {
    private  String code;
    private  String msg;
    private  String data;

    public String getCode() {
        return code;
    }

    public BaseRows(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
