package com.example.pandas.config;

import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.wxapi.App;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class CultureSpActivity extends BaseActivity implements JCVideoPlayerStandard.OnCollect{


    @Override
    public int getLayoutId() {
        return R.layout.activity_culture_sp;
    }

    @Override
    public void initview() {
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class,
                url, title);

        JCVideoPlayerStandard.setOnCollect(this);
//        finish();
    }

    @Override
    public void successful() {
        Toast.makeText(App.context, "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void filed() {
        Toast.makeText(App.context, "取消收藏", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

//    @Override
//    public void onBackPressed() {
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
//
//        SharedPreferences.Editor s1 = getSharedPreferences("s1", MODE_PRIVATE).edit();
//        s1.putBoolean("bool", false);
//        s1.commit();
//        finish();
//    }

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

