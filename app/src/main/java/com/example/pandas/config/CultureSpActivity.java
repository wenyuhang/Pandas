package com.example.pandas.config;

import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CultureSpActivity extends BaseActivity {


    @Bind(R.id.img1)
    ImageView img1;
    @Bind(R.id.img2)
    ImageView img2;
    @Bind(R.id.culture_jc)
    JCVideoPlayerStandard cultureJc;
    @Bind(R.id.culture_tiitle)
    TextView cultureTiitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_culture_sp;
    }

    @Override
    public void initview() {
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        VideoUtils.getUtils().playVideo(cultureJc, url, "", "");
        cultureTiitle.setText(title);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        SharedPreferences.Editor s1 = getSharedPreferences("s1", MODE_PRIVATE).edit();
        s1.putBoolean("bool",false);
        s1.commit();
        finish();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

