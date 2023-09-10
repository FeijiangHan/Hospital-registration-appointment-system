package com.asiait.yygh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.until.ConfigApplication;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class UserUpdate_hz extends AppCompatActivity {
    Button add;//添加按钮
    EditText nickname, tel, card;//表单
    TextView  sex;
    Context con = UserUpdate_hz.this;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        card = findViewById(R.id.card);
        card.setText(ConfigApplication.getU().getCard());

            sex = findViewById(R.id.sex);
            sex.setText(ConfigApplication.getU().getSex());
            sex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String[] items = {"男", "女"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(con);
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

            nickname = findViewById(R.id.nickname);
            nickname.setText(ConfigApplication.getU().getNickname());
            tel = findViewById(R.id.tel);
            tel.setText(ConfigApplication.getU().getTel());

            add = findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        if (tel.getText().toString().length() == 11) {
                            OkGo.<BaseRows>post(RequestUrl.UsermUpdate)
                                    .params("type", "患者")
                                    .params("card", card.getText().toString())
                                    .params("sex", sex.getText().toString())
                                    .params("nickname", nickname.getText().toString())
                                    .params("tel", tel.getText().toString())
                                    .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                        @Override
                                        public void onSuccess(Response<BaseRows> response) {
                                            if (response.body().getCode().equals("200")) {//注册成功
                                                Toast.makeText(con, "更新成功", Toast.LENGTH_SHORT).show();
                                                ConfigApplication.getU().setNickname(nickname.getText().toString());
                                                ConfigApplication.getU().setTel(tel.getText().toString());
                                                ConfigApplication.getU().setCard(card.getText().toString());
                                                ConfigApplication.getU().setSex(sex.getText().toString());
                                                finish();

                                            } else {
                                                Toast.makeText(con, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                        } else {
                            Toast.makeText(con, "手机号格式不对,请输入11位手机号", Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }
}
