package com.asiait.yygh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wildma.pictureselector.PictureSelector;

import org.w3c.dom.Text;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistActivity extends AppCompatActivity {
    Button add;//添加按钮
    ImageView img;//图片显示
    EditText username, nickname, tel, pass,card,msg,room,post;//表单
    TextView type,sex,sex2;
    Context con = RegistActivity.this;
    LinearLayout hz,ys;
    EditText username2, nickname2, pass2;//表单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgit);
        msg = findViewById(R.id.msg);
        room = findViewById(R.id.room);
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items={"皮肤科","中医科","耳鼻喉科","妇科","眼科","精神心里","不孕不育","男性泌尿","心内科","内分泌科","肾病内科","乳腺科","性病科","糖尿病","普通内科"};
                AlertDialog.Builder builder=new AlertDialog.Builder(con);
                builder.setTitle("列表信息");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        room.setText(items[i]);
                    }
                });
                builder.show();
            }
        });
        post = findViewById(R.id.post);

        card = findViewById(R.id.card);

        hz = findViewById(R.id.hz);
        ys = findViewById(R.id.ys);
        sex2 = findViewById(R.id.sex2);
        username2 = findViewById(R.id.username2);
        nickname2 = findViewById(R.id.nickname2);
        pass2 = findViewById(R.id.pass2);

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
                        if (items[i].equals("患者")){
                            hz.setVisibility(View.VISIBLE);
                            ys.setVisibility(View.GONE);

                        }
                        else {
                            hz.setVisibility(View.GONE);
                            ys.setVisibility(View.VISIBLE);

                        }
                    }
                });
                builder.show();
            }
        });
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
        sex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items={"男","女"};
                AlertDialog.Builder builder=new AlertDialog.Builder(con);
                builder.setTitle("列表信息");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sex2.setText(items[i]);
                    }
                });
                builder.show();
            }
        });
        nickname = findViewById(R.id.nickname);
        username = findViewById(R.id.username);
        tel = findViewById(R.id.tel);
        pass = findViewById(R.id.pass);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.getText().toString().equals("患者")) {
                    if (tel.getText().toString().length() == 11) {
                        if (username.getText().toString().length() >= 6) {
                            String txt = pass.getText().toString();
                            Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,12})$");
                            Matcher matcher = Password_Pattern.matcher(txt);
                            if (matcher.matches()) {
                                OkGo.<BaseRows>post(RequestUrl.Usermsave)
                                        .params("type","患者")
                                        .params("card", card.getText().toString())
                                        .params("sex", sex.getText().toString())
                                        .params("username", username.getText().toString())
                                        .params("pass", pass.getText().toString())
                                        .params("nickname", nickname.getText().toString())
                                        .params("tel", tel.getText().toString())
                                        .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                            @Override
                                            public void onSuccess(Response<BaseRows> response) {
                                                if (response.body().getCode().equals("200")) {//注册成功
                                                    startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                                                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(RegistActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            } else {
                                Toast.makeText(RegistActivity.this, "密码格式不对,请输入6到12位英文加数字", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(RegistActivity.this, "账号格式不对,请输入6位以上的账号", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistActivity.this, "手机号格式不对,请输入11位手机号", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                        if (username2.getText().toString().length() >= 6) {
                            String txt = pass2.getText().toString();
                            Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,12})$");
                            Matcher matcher = Password_Pattern.matcher(txt);
                            if (matcher.matches()) {
                                OkGo.<BaseRows>post(RequestUrl.Usermsave)
                                        .params("type","医生")
                                        .params("post", post.getText().toString())
                                        .params("room", room.getText().toString())
                                        .params("msg", msg.getText().toString())
                                        .params("card", card.getText().toString())
                                        .params("sex", sex2.getText().toString())
                                        .params("username", username2.getText().toString())
                                        .params("pass", pass2.getText().toString())
                                        .params("nickname", nickname2.getText().toString())
                                        .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                            @Override
                                            public void onSuccess(Response<BaseRows> response) {
                                                if (response.body().getCode().equals("200")) {//注册成功
                                                    startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                                                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(RegistActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            } else {
                                Toast.makeText(RegistActivity.this, "密码格式不对,请输入6到12位英文加数字", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(RegistActivity.this, "账号格式不对,请输入6位以上的账号", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });
    }



}