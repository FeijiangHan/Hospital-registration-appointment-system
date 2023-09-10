package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.entity.persion;
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

public class Jzr extends AppCompatActivity {
    ImageView back,add;
    RefreshLayout refreshLayout;
    List<persion> list;
    ListView listView;
    Context con = Jzr.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jzr);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(con, Jzr_add.class));

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
        OkGo.<BaseRows>get(RequestUrl.persionGetALl)
                .params("uid", ConfigApplication.getU().getId())
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        super.onSuccess(response);
                        if (response.body().getCode().equals("200")) {
                            refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                            list = JSON.parseArray(response.body().getData(), persion.class);
                            listView.setAdapter(new CommonAdapter<persion>(con, list, R.layout.list_persion) {
                                @Override
                                public void convert(ViewHolder helper, persion item) {
                                    helper.setText(R.id.nickname, item.getNickname());
                                    helper.setText(R.id.card, item.getCard());
                                    helper.setText(R.id.tel, item.getTel());
                                    helper.setText(R.id.sex, item.getSex());
                                    TextView ynh = helper.getView(R.id.ynh);
                                    ynh.setText("医保号："+item.getId().substring(0,6));
                                }
                            });
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    persion u = list.get(i);
                                    Intent id2 = new Intent(con, Jzr_update.class);
                                    Bundle b = new Bundle();
                                    b.putSerializable("o", u);
                                    id2.putExtras(b);
                                    startActivity(id2);
                                }
                            });
                            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    MyAlertDialog myAlertDialog = new MyAlertDialog(con).builder()
                                            .setTitle("确认吗？")
                                            .setMsg("删除就诊人")
                                            .setPositiveButton("删除", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    OkGo.<BaseRows>post(RequestUrl.Jzrdel)
                                                            .params("id",list.get(i).getId())
                                                            .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                                                                @Override
                                                                public void onSuccess(Response<BaseRows> response) {
                                                                    if (response.body().getCode().equals("200")) {
                                                                        Toast.makeText(con, "删除成功", Toast.LENGTH_SHORT).show();
                                                                        mgetAll(refreshLayout);
                                                                    } else {
                                                                        Toast.makeText(con, "更新失败", Toast.LENGTH_SHORT).show();
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
                                    return true;
                                }
                            });
                        }
                    }
                });
    }
}