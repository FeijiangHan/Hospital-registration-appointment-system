package com.asiait.yygh;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserUpdate_ys extends AppCompatActivity {
    Button add;//添加按钮
    EditText nickname, msg,room,post;//表单
    TextView  sex;
    Context con = UserUpdate_ys.this;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_ys);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        msg = findViewById(R.id.msg);
        msg.setText(ConfigApplication.getU().getMsg());
        room = findViewById(R.id.room);
        room.setText(ConfigApplication.getU().getRoom());
        post = findViewById(R.id.post);
        post.setText(ConfigApplication.getU().getPost());


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
            add = findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            OkGo.<BaseRows>post(RequestUrl.UsermUpdate)
                                    .params("type", "患者")
                                    .params("msg", msg.getText().toString())
                                    .params("sex", sex.getText().toString())
                                    .params("nickname", nickname.getText().toString())
                                    .params("post", post.getText().toString())
                                    .params("room", room.getText().toString())

                                    .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                        @Override
                                        public void onSuccess(Response<BaseRows> response) {
                                            if (response.body().getCode().equals("200")) {//注册成功
                                                Toast.makeText(con, "更新成功", Toast.LENGTH_SHORT).show();
                                                ConfigApplication.getU().setNickname(nickname.getText().toString());
                                                ConfigApplication.getU().setRoom(room.getText().toString());
                                                ConfigApplication.getU().setMsg(msg.getText().toString());
                                                ConfigApplication.getU().setPost(post.getText().toString());

                                                ConfigApplication.getU().setSex(sex.getText().toString());
                                                finish();

                                            } else {
                                                Toast.makeText(con, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });



                }
            });
        }
}
