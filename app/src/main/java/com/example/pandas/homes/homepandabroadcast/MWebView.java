package com.example.pandas.homes.homepandabroadcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.pandas.R;

public class MWebView extends AppCompatActivity {

    private WebView mvebview;
    private ImageView butiamge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mweb_view);
        initView();

    }

    private void initView() {
        mvebview = (WebView) findViewById(R.id.mvebview);
        butiamge = (ImageView) findViewById(R.id.butiamge);
        WebSettings settings = mvebview.getSettings();
        // 让WebView能够执行javaScript
        settings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //自动适配屏幕
        settings.setUseWideViewPort(true);//图片自动适配webview的大小
        settings.setLoadWithOverviewMode(true);
        mvebview.loadUrl("http://news.ipanda.com/2017/06/12/ARTIBdwYiZE71cob9CQLUz79170612.shtml");
        butiamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
