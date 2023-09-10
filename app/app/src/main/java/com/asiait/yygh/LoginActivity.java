package com.asiait.yygh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.DipPx;
import com.asiait.yygh.until.user;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username,pass;
    Button login,regist;
    Context con = LoginActivity.this;
    ImageView bj;
    TextView type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bj = findViewById(R.id.bj);
        RequestOptions options = new RequestOptions()
                .bitmapTransform(new RoundedCorners(DipPx.dip2px(con, 1)));
        Glide.with(con).asBitmap()
                .load("https://img2.baidu.com/it/u=802116394,1564454599&fm=253&fmt=auto&app=138&f=JPEG?w=875&h=500")
                .apply(options)
                .into(bj);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);
        type = findViewById(R.id.type);
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items={"患者","医生"};
                AlertDialog.Builder builder=new AlertDialog.Builder(con);
                builder.setTitle("列表信息");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        type.setText(items[i]);
                    }
                });
                builder.show();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "请选择用户类型", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (username.getText().toString().length() > 0 && pass.getText().toString().length() > 0) {
                        OkGo.<BaseRows>post(RequestUrl.Userlogin)
                                .params("type",type.getText().toString())
                                .params("username", username.getText().toString())
                                .params("pass", pass.getText().toString())
                                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                    @Override
                                    public void onSuccess(Response<BaseRows> response) {
                                        if (response.body().getCode().equals("200")) {//登录成功
                                            ConfigApplication.getApp().setU(JSON.parseObject(response.body().getData(), user.class));
                                            if (type.getText().toString().equals("医生")){
                                                startActivity(new Intent(LoginActivity.this, MainActivity_ys.class));
                                            }
                                            else {
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            }
                                        } else {
                                            Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(LoginActivity.this, "请输入信息", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
