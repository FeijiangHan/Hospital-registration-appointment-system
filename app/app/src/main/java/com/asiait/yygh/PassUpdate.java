package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.until.ConfigApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class PassUpdate extends AppCompatActivity {
    ImageView back;
    Button add;//添加按钮
    EditText old, newpass,confirm;//表单
    Context con = PassUpdate.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_update);
        add = findViewById(R.id.add);
        old = findViewById(R.id.old);
        newpass = findViewById(R.id.newpass);
        confirm = findViewById(R.id.confirm);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConfigApplication.getU().getPass().equals(old.getText().toString())){

                    if(newpass.getText().toString().equals(confirm.getText().toString())){
                        OkGo.<BaseRows>post(RequestUrl.Passupdate)
                                .params("pass",newpass.getText().toString())
                                .params("id", ConfigApplication.getU().getId())
                                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                    @Override
                                    public void onSuccess(Response<BaseRows> response) {
                                        if (response.body().getCode().equals("200")) {
                                            ConfigApplication.getU().setPass(newpass.getText().toString());
                                            finish();
                                            Toast.makeText(con, "更新成功,下次登录为新密码", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(con, "更新失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                });
                    }else{
                        Toast.makeText(con, "两次密码输入不正确", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(con, "旧密码不正确", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}