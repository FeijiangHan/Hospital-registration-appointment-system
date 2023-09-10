package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.until.CommonAdapter;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.ViewHolder;
import com.asiait.yygh.until.user;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class Qxgh extends AppCompatActivity {
    ImageView back;
    RefreshLayout refreshLayout;
    List<user> list;
    ListView listView;
    Context con = Qxgh.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxgh);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView = findViewById(R.id.listView);

        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
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
    }
    @Override
    public void onResume() {
        super.onResume();
        mgetAll(refreshLayout);
    }
    public void mgetAll(RefreshLayout refreshlayout) {
        OkGo.<BaseRows>get(RequestUrl.infoGetMy)
                .params("uid", ConfigApplication.getU().getId())
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        super.onSuccess(response);
                        if (response.body().getCode().equals("200")) {
                            refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                            list = JSON.parseArray(response.body().getData(), user.class);
                            listView.setAdapter(new CommonAdapter<user>(con, list, R.layout.list_qxgh) {
                                @Override
                                public void convert(ViewHolder helper, user item) {
                                    helper.setText(R.id.nickname, item.getNickname());
                                    helper.setText(R.id.room, item.getRoom());
                                    helper.setText(R.id.post, item.getPost());
                                    helper.setText(R.id.sex, item.getSex());
                                    helper.setText(R.id.date, item.getDate());
                                    helper.setText(R.id.state, item.getState());
                                }
                            });
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    MyAlertDialog myAlertDialog = new MyAlertDialog(con).builder()
                                            .setTitle("确认吗？")
                                            .setMsg("取消挂号")
                                            .setPositiveButton("确认", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    OkGo.<BaseRows>post(RequestUrl.infoupdate)
                                                            .params("id",list.get(i).getPid())
                                                            .params("state","取消")
                                                            .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                                                @Override
                                                                public void onSuccess(Response<BaseRows> response) {
                                                                    if (response.body().getCode().equals("200")) {
                                                                        Toast.makeText(con, "取消成功", Toast.LENGTH_SHORT).show();
                                                                        mgetAll(refreshLayout);
                                                                    } else {
                                                                        Toast.makeText(con, "取消失败", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }

                                                            });
                                                }
                                            }).setNegativeButton("返回", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                }
                                            });
                                    myAlertDialog.show();
                                }
                            });
                        }
                    }
                });
    }
}