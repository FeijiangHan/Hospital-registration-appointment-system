package com.asiait.yygh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.entity.work;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.user;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_rcap extends Fragment {
    TextView time,yue,yi,er,san,si,wu,liu,tian;
    List<String>list;
    TextView s7t,s7r,s6t,s6r,s5t,s5r,s4t,s4r,s3t,s3r,s2t,s2r,s1t,s1r;
    work w;
    TextView z7t,z7r,z6t,z6r,z5t,z5r,z4t,z4r,z3t,z3r,z2t,z2r,z1t,z1r;
    TextView w7t,w7r,w6t,w6r,w5t,w5r,w4t,w4r,w3t,w3r,w2t,w2r,w1t,w1r;

    public static Main_rcap newInstance(String tag) {
        Bundle args = new Bundle();
        Main_rcap fragment = new Main_rcap();
        args.putString("title",tag);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_rcap, container, false);
        w7t = v.findViewById(R.id.w7t);
        w7r = v.findViewById(R.id.w7r);
        w6t = v.findViewById(R.id.w6t);
        w6r = v.findViewById(R.id.w6r);
        w5t = v.findViewById(R.id.w5t);
        w5r = v.findViewById(R.id.w5r);
        w4t = v.findViewById(R.id.w4t);
        w4r = v.findViewById(R.id.w4r);
        w3t = v.findViewById(R.id.w3t);
        w3r = v.findViewById(R.id.w3r);
        w2t = v.findViewById(R.id.w2t);
        w2r = v.findViewById(R.id.w2r);
        w1t = v.findViewById(R.id.w1t);
        w1r = v.findViewById(R.id.w1r);

        z7t = v.findViewById(R.id.z7t);
        z7r = v.findViewById(R.id.z7r);
        z6t = v.findViewById(R.id.z6t);
        z6r = v.findViewById(R.id.z6r);
        z5t = v.findViewById(R.id.z5t);
        z5r = v.findViewById(R.id.z5r);
        z4t = v.findViewById(R.id.z4t);
        z4r = v.findViewById(R.id.z4r);
        z3t = v.findViewById(R.id.z3t);
        z3r = v.findViewById(R.id.z3r);
        z2t = v.findViewById(R.id.z2t);
        z2r = v.findViewById(R.id.z2r);
        z1t = v.findViewById(R.id.z1t);
        z1r = v.findViewById(R.id.z1r);

        s7t = v.findViewById(R.id.s7t);
        s7r = v.findViewById(R.id.s7r);
        s6t = v.findViewById(R.id.s6t);
        s6r = v.findViewById(R.id.s6r);
        s5t = v.findViewById(R.id.s5t);
        s5r = v.findViewById(R.id.s5r);
        s4t = v.findViewById(R.id.s4t);
        s4r = v.findViewById(R.id.s4r);
        s3t = v.findViewById(R.id.s3t);
        s3r = v.findViewById(R.id.s3r);
        s2t = v.findViewById(R.id.s2t);
        s2r = v.findViewById(R.id.s2r);
        s1t = v.findViewById(R.id.s1t);
        s1r = v.findViewById(R.id.s1r);

        time = v.findViewById(R.id.time);
        yue = v.findViewById(R.id.yue);
        yi = v.findViewById(R.id.yi);
        er = v.findViewById(R.id.er);
        san = v.findViewById(R.id.san);
        si = v.findViewById(R.id.si);
        wu = v.findViewById(R.id.wu);
        liu = v.findViewById(R.id.liu);
        tian = v.findViewById(R.id.tian);

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        gettime();
        getdate();
        getyue();
    }
    public void gettime(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date2 = sdf.format(date);
                time.setText(date2);
                gettime();
            }
        }, 1000);
    }
    public void getyue(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String date2 = sdf.format(date);
        yue.setText(date2);
    }
    public void getdate(){
        OkGo.<BaseRows>post(RequestUrl.getdate)
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println(response.body().getData());
                            list = JSON.parseArray(response.body().getData(), String.class);
                            yi.setText(list.get(0).substring(list.get(0).length()-2,list.get(0).length()));
                            er.setText(list.get(1).substring(list.get(1).length()-2,list.get(1).length()));
                            san.setText(list.get(2).substring(list.get(2).length()-2,list.get(2).length()));
                            si.setText(list.get(3).substring(list.get(3).length()-2,list.get(3).length()));
                            wu.setText(list.get(4).substring(list.get(4).length()-2,list.get(4).length()));
                            liu.setText(list.get(5).substring(list.get(5).length()-2,list.get(5).length()));
                            tian.setText(list.get(6).substring(list.get(6).length()-2,list.get(6).length()));
                            gets1();
                            gets2();
                            gets3();
                            gets4();
                            gets5();
                            gets6();
                            gets7();
                            getz7();
                            getz6();
                            getz5();
                            getz4();
                            getz3();
                            getz2();
                            getz1();
                            getw7();
                            getw6();
                            getw5();
                            getw4();
                            getw3();
                            getw2();
                            getw1();

                        } else {
                            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void getw7(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(6))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w7r.setText(w.getRoom());
                            w7t.setText(w.getType());

                        }
                    }
                });

    }
    public void getw6(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(5))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w6r.setText(w.getRoom());
                            w6t.setText(w.getType());

                        }
                    }
                });

    }
    public void getw5(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(4))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w5r.setText(w.getRoom());
                            w5t.setText(w.getType());

                        }
                    }
                });

    }
    public void getw4(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(3))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w4r.setText(w.getRoom());
                            w4t.setText(w.getType());

                        }
                    }
                });

    }
    public void getw3(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(2))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w3r.setText(w.getRoom());
                            w3t.setText(w.getType());

                        }
                    }
                });

    }
    public void getw2(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(1))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w2r.setText(w.getRoom());
                            w2t.setText(w.getType());

                        }
                    }
                });

    }
    public void getw1(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","晚上")
                .params("date",list.get(0))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            w1r.setText(w.getRoom());
                            w1t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz7(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(6))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z7r.setText(w.getRoom());
                            z7t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz6(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(5))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z6r.setText(w.getRoom());
                            z6t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz5(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(4))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z5r.setText(w.getRoom());
                            z5t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz4(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(3))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z4r.setText(w.getRoom());
                            z4t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz3(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(2))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z3r.setText(w.getRoom());
                            z3t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz2(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(1))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z2r.setText(w.getRoom());
                            z2t.setText(w.getType());

                        }
                    }
                });

    }
    public void getz1(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","下午")
                .params("date",list.get(0))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            z1r.setText(w.getRoom());
                            z1t.setText(w.getType());

                        }
                    }
                });

    }
    public void gets1(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(0))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s1r.setText(w.getRoom());
                            s1t.setText(w.getType());

                        }
                    }
                });

    }
    public void gets2(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(1))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s2r.setText(w.getRoom());
                            s2t.setText(w.getType());

                        }
                    }
                });

    }
    public void gets3(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(2))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s3r.setText(w.getRoom());
                            s3t.setText(w.getType());
                        }
                    }
                });

    }
    public void gets4(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(3))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s4r.setText(w.getRoom());
                            s4t.setText(w.getType());

                        }
                    }
                });

    }
    public void gets5(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(4))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s5r.setText(w.getRoom());
                            s5t.setText(w.getType());

                        }
                    }
                });

    }
    public void gets6(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(5))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s6r.setText(w.getRoom());
                            s6t.setText(w.getType());

                        }
                    }
                });

    }
    public void gets7(){
        OkGo.<BaseRows>post(RequestUrl.getwork)
                .params("uid",ConfigApplication.getU().getId())
                .params("quant","上午")
                .params("date",list.get(6))
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {
                            System.out.println("数据"+response.body().getData());
                            w = JSON.parseObject(response.body().getData(), work.class);
                            s7r.setText(w.getRoom());
                            s7t.setText(w.getType());

                        }
                    }
                });

    }
}
