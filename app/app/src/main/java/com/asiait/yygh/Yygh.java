package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.until.CommonAdapter;
import com.asiait.yygh.until.ViewHolder;
import com.asiait.yygh.until.user;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Yygh extends AppCompatActivity {
    ImageView back;
    EditText nickname,post;
    Button search;
    RefreshLayout refreshLayout;
    List<user> list;
    ListView listView;
    Context con = Yygh.this;
    String type;
    TextView title;
    String quant="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yygh);
        getsjd();
        Intent getIntent = getIntent();
        type = getIntent.getStringExtra("type");
        title = findViewById(R.id.title);
        title.setText("预约挂号-"+type);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nickname = findViewById(R.id.nickname);
        post = findViewById(R.id.post);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mgetAll(refreshLayout);
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
        getsjd();
        mgetAll(refreshLayout);
    }
    public void mgetAll(RefreshLayout refreshlayout) {
        OkGo.<BaseRows>get(RequestUrl.workGetALl)
                .params("type",type)
                .params("quant",quant)
                .params("nickname",nickname.getText().toString())
                .params("post",post.getText().toString())
                .execute(new JsonCallBacak<BaseRows>(BaseRows.class) {
                    @Override
                    public void onSuccess(Response<BaseRows> response) {
                        super.onSuccess(response);
                        if (response.body().getCode().equals("200")) {
                            refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                            list = JSON.parseArray(response.body().getData(), user.class);
                            listView.setAdapter(new CommonAdapter<user>(con, list, R.layout.list_user) {
                                @Override
                                public void convert(ViewHolder helper, user item) {
                                    helper.setText(R.id.nickname, item.getNickname());
                                    helper.setText(R.id.room, item.getRoom());
                                    helper.setText(R.id.post, item.getPost());
                                    helper.setText(R.id.sex, item.getSex());
                                    helper.setText(R.id.msg, item.getMsg());
                                    helper.setText(R.id.quant, item.getQuant());

                                }
                            });
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    user u = list.get(i);
                                    Intent id2 = new Intent(con, Yygh_xq.class);
                                    Bundle b = new Bundle();
                                    b.putSerializable("o", u);
                                    id2.putExtras(b);
                                    startActivity(id2);


                                }
                            });
                        }
                    }
                });
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

    public void getsjd() {

        String strStartTime1 = "00:00";
        String strEndTime1 = "12:00";
        String strEndTime2 = "18:00";
        String strEndTime3 = "24:00";

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String now = sdf.format(new Date());
        Date nowTime;
        try{
            nowTime = sdf.parse(now);
            Date startTime1 = sdf.parse(strStartTime1);
            Date endTime1 = sdf.parse(strEndTime1);
            Date endTime2 = sdf.parse(strEndTime2);
            Date endTime3 = sdf.parse(strEndTime3);
            if (isEffectiveDate(nowTime, startTime1, endTime1)) {
                quant = "上午";
                System.out.println("当前时间在时间段内["+strStartTime1+","+strEndTime1+"]");
            }else if (isEffectiveDate(nowTime, endTime1, endTime2)){
                System.out.println("当前时间在时间段内["+strEndTime1+","+strEndTime2+"]");
                quant = "下午";
            }
            else if (isEffectiveDate(nowTime, endTime2, endTime3)){
                System.out.println("当前时间在时间段内["+strEndTime2+","+strEndTime3+"]");
                quant = "晚上";
            }
            else {
                quant = "";

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}