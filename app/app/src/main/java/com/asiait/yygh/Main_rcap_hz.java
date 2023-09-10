package com.asiait.yygh;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.entity.info;
import com.asiait.yygh.until.CommonAdapter;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.ViewHolder;
import com.asiait.yygh.until.user;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_rcap_hz extends Fragment {
    TextView time,cs;
    RefreshLayout refreshLayout;
    List<info> list;
    ListView listView;

    public static Main_rcap_hz newInstance(String tag) {
        Bundle args = new Bundle();
        Main_rcap_hz fragment = new Main_rcap_hz();
        args.putString("title",tag);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_rcap_hz, container, false);
        time = v.findViewById(R.id.time);
        cs = v.findViewById(R.id.cs);
        listView = v.findViewById(R.id.listView);

        refreshLayout = (RefreshLayout)v.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mgetAll(refreshlayout);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        refreshLayout.autoRefresh();

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        gettime();
        getweek();
        mgetAll(refreshLayout);
    }
    public void mgetAll(RefreshLayout refreshlayout) {
        OkGo.<BaseRows>get(RequestUrl.infoGetMy2)
                .params("uid", ConfigApplication.getU().getId())
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        super.onSuccess(response);
                        if (response.body().getCode().equals("200")) {
                            refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                            list = JSON.parseArray(response.body().getData(), info.class);
                            listView.setAdapter(new CommonAdapter<info>(getContext(), list, R.layout.list_rc) {
                                @Override
                                public void convert(ViewHolder helper, info item) {
                                    helper.setText(R.id.nickname, item.getTname()+"医生");
                                    helper.setText(R.id.room, item.getYroom()+"诊室");
                                    helper.setText(R.id.msg, item.getMsg());
                                    helper.setText(R.id.date, item.getDate());
                                    helper.setText(R.id.quant, item.getQuant());
                                }
                            });
                        }
                    }
                });
    }
    public void gettime(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date2 = sdf.format(date);
                time.setText("当前时间："+date2);
                gettime();
            }
        }, 1000);
    }
    public void getweek(){
        OkGo.<BaseRows>post(RequestUrl.infoweek)
                .params("uid",ConfigApplication.getU().getId())
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        if (response.body().getCode().equals("200")) {//登录成功
                           cs.setText("本周预约次数:"+response.body().getData());
                        } else {
                            Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
