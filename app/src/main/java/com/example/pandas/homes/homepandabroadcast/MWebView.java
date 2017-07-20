package com.example.pandas.homes.homepandabroadcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pandas.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MWebView extends AppCompatActivity {

    @Bind(R.id.collect)
    CheckBox collect;
    @Bind(R.id.share)
    Button share;
    private WebView mvebview;
    private ImageView butiamge;
    private String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mweb_view);
        ButterKnife.bind(this);
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
        url = getIntent().getStringExtra("url");
        Log.d("MWebView", url);
        mvebview.loadUrl(url);
        butiamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.collect, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect:
                if(collect.isChecked()){
                    Toast toast = Toast.makeText(MWebView.this, "已收藏", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(MWebView.this, "已取消收藏", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.share:
                UMWeb umWeb = new UMWeb(url);
                new ShareAction(this).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.SINA).withText("666").open();
                new ShareAction(this).withMedia(umWeb).share();
                break;
        }
    }

}
