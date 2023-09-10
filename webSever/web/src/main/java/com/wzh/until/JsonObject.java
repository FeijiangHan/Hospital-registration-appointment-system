package com.wzh.until;

public class JsonObject {
  private  String code;
  private  String msg;
  private  Object data;

    public String getCode() {
        return code;
    }

    public JsonObject(String code, String msg, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
