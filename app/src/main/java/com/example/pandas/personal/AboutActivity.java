package com.example.pandas.personal;

import android.widget.ImageButton;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements UMShareListener{

    @Bind(R.id.fanhui)
    ImageButton fanhui;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initview() {

    }

    @OnClick(R.id.fanhui)
    public void onViewClicked() {
        UMImage umImage = new UMImage(this, R.mipmap.about_sign);
        new ShareAction(this).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN).withMedia(umImage).setCallback(this).open();
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }
}
