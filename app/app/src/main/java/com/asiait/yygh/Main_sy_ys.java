package com.asiait.yygh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.entity.info;
import com.asiait.yygh.until.CommonAdapter;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.ViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_sy_ys extends Fragment {
    RefreshLayout refreshLayout;
    List<info> list;
    ListView listView;
    public static Main_sy_ys newInstance(String tag) {
        Bundle args = new Bundle();
        Main_sy_ys fragment = new Main_sy_ys();
        args.putString("title",tag);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_sy_ys, container, false);
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
    public void mgetAll(RefreshLayout refreshlayout) {
        OkGo.<BaseRows>get(RequestUrl.infoday)
                .params("tid",ConfigApplication.getU().getId())
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
                                    helper.setText(R.id.nickname, item.getTname()+"患者");
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

}
