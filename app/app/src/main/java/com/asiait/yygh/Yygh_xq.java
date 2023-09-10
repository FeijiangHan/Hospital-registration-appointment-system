package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.entity.persion;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.user;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Yygh_xq extends AppCompatActivity {
    user u;
    ImageView back;
    TextView jzr;
    Button add;
    Context con = Yygh_xq.this;
    List<persion> listy;
    EditText msg;
    String quant2="";
    //    TextView date;
    Calendar c=Calendar.getInstance();
    String jid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yygh_xq);



//        getsjd();
        u = (user) getIntent().getExtras().getSerializable("o");
        msg = findViewById(R.id.msg);



        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jzr = findViewById(R.id.jzr);

        jzr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.<BaseRows>get(RequestUrl.persionGetALl)
                        .params("uid", ConfigApplication.getU().getId())
                        .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                            @Override
                            public void onSuccess(Response<BaseRows> response) {
                                super.onSuccess(response);
                                listy = JSON.parseArray(response.body().getData(), persion.class);
                                final String[] items = new String[listy.size()];
                                for (int i = 0; i < listy.size(); i++) {
                                    items[i] = listy.get(i).getNickname();
                                }
                                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(con);
                                builder.setTitle("就诊人选择");
                                builder.setItems(items, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        jzr.setText(items[i]);
                                        jid = listy.get(i).getId();
                                    }
                                });
                                builder.show();
                            }
                        });
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( jzr.getText().toString().equals("")  || msg.getText().toString().equals("")) {
                    Toast.makeText(con, "请填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    Dialog Dialog = new Dialog(con);
                    Dialog.show();
                    Dialog.setOnPosNegClickListener(new Dialog.OnPosNegClickListener() {
                        @Override
                        public void posClickListener() {
                            OkGo.<BaseRows>post(RequestUrl.infoadd)
                                    .params("msg", msg.getText().toString())
                                    .params("quant",u.getQuant())
                                    .params("uid", ConfigApplication.getU().getId())
                                    .params("tid", u.getTid())
                                    .params("wid",u.getId())
                                    .params("tname", u.getNickname())
                                    .params("yroom", u.getRoom())
                                    .params("jid",jid)
                                    .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                        @Override
                                        public void onSuccess(Response<BaseRows> response) {
                                            if (response.body().getCode().equals("200")) {//注册成功
                                                Toast.makeText(con, "预约成功", Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                Toast.makeText(con, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                        @Override
                        public void negCliclListener() {
                            Toast.makeText(con, "返回", Toast.LENGTH_SHORT).show();

                        }


                    });

                }
            }
        });

    }
    private String getTimes(Date date) {//可根据需要自行格式化数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
    private void show(int year,int month,int day){
        String str=year+"-"+(month+1)+"-"+day;
        Toast.makeText(Yygh_xq.this,str,Toast.LENGTH_LONG).show();
    }
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

//    public void getsjd() {
//
//        String strStartTime1 = "00:00";
//        String strEndTime1 = "12:00";
//        String strEndTime2 = "18:00";
//        String strEndTime3 = "24:00";
//
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String now = sdf.format(new Date());
//        Date nowTime;
//        try{
//            nowTime = sdf.parse(now);
//            Date startTime1 = sdf.parse(strStartTime1);
//            Date endTime1 = sdf.parse(strEndTime1);
//            Date endTime2 = sdf.parse(strEndTime2);
//            Date endTime3 = sdf.parse(strEndTime3);
//            if (isEffectiveDate(nowTime, startTime1, endTime1)) {
//                quants.setVisibility(View.VISIBLE);
//                quantz.setVisibility(View.GONE);
//                quantw.setVisibility(View.GONE);
//
//                System.out.println("当前时间在时间段内["+strStartTime1+","+strEndTime1+"]");
//            }else if (isEffectiveDate(nowTime, endTime1, endTime2)){
//                System.out.println("当前时间在时间段内["+strEndTime1+","+strEndTime2+"]");
//                quants.setVisibility(View.GONE);
//                quantz.setVisibility(View.VISIBLE);
//                quantw.setVisibility(View.GONE);
//            }
//            else if (isEffectiveDate(nowTime, endTime2, endTime3)){
//                System.out.println("当前时间在时间段内["+strEndTime2+","+strEndTime3+"]");
//                quants.setVisibility(View.GONE);
//                quantz.setVisibility(View.GONE);
//                quantw.setVisibility(View.VISIBLE);
//            }
//            else {
//                quant2 = "";
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}