package com.example.pandas.config;

import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.wxapi.App;

import java.text.SimpleDateFormat;
import java.util.Date;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class CultureSpActivity extends BaseActivity implements JCVideoPlayerStandard.OnCollect{

    private String url;
    private String otherurl;
    private String imageurl;
    private String movietime;
    private String title;
    @Override
    public int getLayoutId() {
        return R.layout.activity_culture_sp;
    }

    @Override
    public void initview() {
        url = getIntent().getStringExtra("url");
        otherurl = getIntent().getStringExtra("otherurl");
        imageurl = getIntent().getStringExtra("imageurl");
        movietime = getIntent().getStringExtra("movietime");
        title = getIntent().getStringExtra("title");

        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class,
                url, title);

        JCVideoPlayerStandard.setOnCollect(this);

    }

    @Override
    public void successful() {
        Toast.makeText(App.context, "收藏成功", Toast.LENGTH_SHORT).show();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        ACacheUtils.getUtils().setStorage(imageurl,movietime,title,format,url,otherurl);
    }

    @Override
    public void filed() {
        Toast.makeText(App.context, "取消收藏", Toast.LENGTH_SHORT).show();
        ACacheUtils.getUtils().deleteStorage(title);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        finish();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (JCVideoPlayer.backPress()) {
//            return;
        }

        SharedPreferences.Editor s1 = getSharedPreferences("s1", MODE_PRIVATE).edit();
        s1.putBoolean("bool", false);
        s1.commit();
//        super.onBackPressed();
        finish();
        return false;
    }



}

