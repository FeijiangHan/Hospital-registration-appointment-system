package com.asiait.yygh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.asiait.yygh.constant.BaseRows;
import com.asiait.yygh.constant.JsonCallBacak;
import com.asiait.yygh.constant.RequestUrl;
import com.asiait.yygh.entity.product;
import com.asiait.yygh.until.CommonAdapter;
import com.asiait.yygh.until.ConfigApplication;
import com.asiait.yygh.until.DipPx;
import com.asiait.yygh.until.HalfType;
import com.asiait.yygh.until.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_sy extends Fragment {
    LinearLayout qx,wd;
    RelativeLayout zs1;
    ImageView bj2,bj3,bj4;
    LinearLayout ptnk,tnb,xbk,rxk,sbnk,nfmk,xnk,nxmn,byby,jsxl,yk,fk,ebhk,zyk,pfk;
    public static Main_sy newInstance(String tag) {
        Bundle args = new Bundle();
        Main_sy fragment = new Main_sy();
        args.putString("title",tag);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_sy, container, false);
        ptnk = v.findViewById(R.id.ptnk);
        ptnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "普通内科");
                startActivity(intent);
            }
        });
        tnb = v.findViewById(R.id.tnb);
        tnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "糖尿病");
                startActivity(intent);
            }
        });
        xbk = v.findViewById(R.id.xbk);
        xbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "性病科");
                startActivity(intent);
            }
        });
        rxk = v.findViewById(R.id.rxk);
        rxk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "乳腺科");
                startActivity(intent);
            }
        });
        sbnk = v.findViewById(R.id.sbnk);
        sbnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "肾病内科");
                startActivity(intent);
            }
        });
        nfmk = v.findViewById(R.id.nfmk);
        nfmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "内分泌科");
                startActivity(intent);
            }
        });
        xnk = v.findViewById(R.id.xnk);
        xnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "心内科");
                startActivity(intent);
            }
        });
        nxmn = v.findViewById(R.id.nxmn);
        nxmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "男性泌尿");
                startActivity(intent);
            }
        });
        byby = v.findViewById(R.id.byby);
        byby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "不孕不育");
                startActivity(intent);
            }
        });
        jsxl = v.findViewById(R.id.jsxl);
        jsxl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "精神心理");
                startActivity(intent);
            }
        });
        yk = v.findViewById(R.id.yk);
        yk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "眼科");
                startActivity(intent);
            }
        });
        fk = v.findViewById(R.id.fk);
        fk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "妇科");
                startActivity(intent);
            }
        });
        ebhk = v.findViewById(R.id.ebhk);
        ebhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "耳鼻喉科");
                startActivity(intent);
            }
        });
        zyk = v.findViewById(R.id.zyk);
        zyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "中医科");
                startActivity(intent);
            }
        });
        pfk= v.findViewById(R.id.pfk);
        pfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Yygh.class);
                intent.putExtra("type", "皮肤科");
                startActivity(intent);
            }
        });
        zs1 = v.findViewById(R.id.zs1);
        bj2 = v.findViewById(R.id.bj2);
        bj3 = v.findViewById(R.id.bj3);
        bj4 = v.findViewById(R.id.bj4);

        zs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Web.class));
            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zstt); // 先从资源中把背景图获取出来
        Bitmap roundBitmap = getRoundCornerImage(bitmap, 30, HalfType.TOP); // 将图片的上半部分圆弧化。
        Drawable dw = new BitmapDrawable(getResources(),roundBitmap);
        zs1.setBackgroundDrawable(dw); // 设置背景。API>=16的話，可以直接用setBackground方法

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.zs2); // 先从资源中把背景图获取出来
        Bitmap roundBitmap2 = getRoundCornerImage(bitmap2, 30, HalfType.ALL); // 将图片的上半部分圆弧化。
        Drawable dw2 = new BitmapDrawable(getResources(),roundBitmap2);
        bj2.setBackgroundDrawable(dw2); // 设置背景。API>=16的話，可以直接用setBackground方法

        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.zs3); // 先从资源中把背景图获取出来
        Bitmap roundBitmap3 = getRoundCornerImage(bitmap3, 30, HalfType.ALL); // 将图片的上半部分圆弧化。
        Drawable dw3 = new BitmapDrawable(getResources(),roundBitmap3);
        bj3.setBackgroundDrawable(dw3); // 设置背景。API>=16的話，可以直接用setBackground方法

        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.mipmap.zs4); // 先从资源中把背景图获取出来
        Bitmap roundBitmap4 = getRoundCornerImage(bitmap4, 30, HalfType.ALL); // 将图片的上半部分圆弧化。
        Drawable dw4 = new BitmapDrawable(getResources(),roundBitmap4);
        bj4.setBackgroundDrawable(dw4); // 设置背景。API>=16的話，可以直接用setBackground方法


        qx = v.findViewById(R.id.qx);
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Qxgh.class));
            }
        });
        wd = v.findViewById(R.id.wd);
        wd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Wdgh.class));
            }
        });
        return v;
    }


    public static Bitmap getRoundCornerImage(Bitmap bitmap, int roundPixels, HalfType half) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap roundConcerImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个和原始图片一样大小的位图
        Canvas canvas = new Canvas(roundConcerImage);//创建位图画布
        Paint paint = new Paint();//创建画笔

        Rect rect = new Rect(0, 0, width, height);//创建一个和原始图片一样大小的矩形
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);// 抗锯齿

        canvas.drawRoundRect(rectF, roundPixels, roundPixels, paint);//画一个基于前面创建的矩形大小的圆角矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//设置相交模式
        canvas.drawBitmap(bitmap, null, rect, paint);//把图片画到矩形去

        switch (half) {
            case LEFT:
                return Bitmap.createBitmap(roundConcerImage, 0, 0, width - roundPixels, height);
            case RIGHT:
                return Bitmap.createBitmap(roundConcerImage, width - roundPixels, 0, width - roundPixels, height);
            case TOP: // 上半部分圆角化 “- roundPixels”实际上为了保证底部没有圆角，采用截掉一部分的方式，就是截掉和弧度一样大小的长度
                return Bitmap.createBitmap(roundConcerImage, 0, 0, width, height - roundPixels);
            case BOTTOM:
                return Bitmap.createBitmap(roundConcerImage, 0, height - roundPixels, width, height - roundPixels);
            case ALL:
                return roundConcerImage;
            default:
                return roundConcerImage;
        }
    }
}
