package com.asiait.yygh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Web extends AppCompatActivity {
    ImageView back;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        web = findViewById(R.id.web);
        web.loadUrl("https://mi.mbd.baidu.com/r/QbxnNAKI9O?f=cp&rs=4016921830&ruk=ul3keaTD6v1BgNbEfZPAlw&u=62c5fb4e35eb1879&urlext=%7B%22cuid%22%3A%22YuSMi0agHagIa2aG0uBYigiO2igIiv8__u258ga7SaKo0qqSB%22%7D");
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
    }
}