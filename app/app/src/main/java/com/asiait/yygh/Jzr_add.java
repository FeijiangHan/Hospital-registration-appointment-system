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
import com.asiait.yygh.until.user;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class Jzr_add extends AppCompatActivity {
    ImageView back;
    EditText nickname,tel,card;
    TextView sex;
    Context con = Jzr_add.this;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzr_add);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nickname = findViewById(R.id.nickname);
        tel = findViewById(R.id.tel);
        card = findViewById(R.id.card);
        sex = findViewById(R.id.sex);
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items={"男","女"};
                AlertDialog.Builder builder=new AlertDialog.Builder(con);
                builder.setTitle("列表信息");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sex.setText(items[i]);
                    }
                });
                builder.show();
            }
        });
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nickname.getText().toString().equals("")||sex.getText().toString().equals("")||tel.getText().toString().equals("")||card.getText().toString().equals("")){
                    Toast.makeText(con, "请填写完整信息", Toast.LENGTH_SHORT).show();
                }
                else {
                    OkGo.<BaseRows>post(RequestUrl.persionadd)
                            .params("uid",ConfigApplication.getU().getId())
                            .params("nickname", nickname.getText().toString())
                            .params("sex", sex.getText().toString())
                            .params("card", card.getText().toString())
                            .params("tel", tel.getText().toString())
                            .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                @Override
                                public void onSuccess(Response<BaseRows> response) {
                                    if (response.body().getCode().equals("200")) {//登录成功
                                        Toast.makeText(con,"添加成功", Toast.LENGTH_SHORT).show();
                                        finish();

                                    } else {
                                        Toast.makeText(con,"失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}