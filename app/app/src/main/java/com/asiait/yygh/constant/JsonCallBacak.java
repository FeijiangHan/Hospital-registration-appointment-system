package com.asiait.yygh.constant;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public class JsonCallBacak<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallBacak(Type type) {
        this.type = type;
    }

    public JsonCallBacak(Class clazz) {
        this.clazz = clazz;
    }
    @Override
    public void onSuccess(Response<T> response) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        Log.i("dddddssss", JSON.toJSONString(response));
        if (body == null)
            return null;
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) data = gson.fromJson(jsonReader, type);
        if (clazz != null) data = gson.fromJson(jsonReader, clazz);
        return data;
    }
}
