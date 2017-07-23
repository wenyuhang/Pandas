package com.example.pandas.personal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalXinActivity extends BaseActivity implements UMAuthListener{

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.iv_headicon)
    ImageView ivHeadicon;
    @Bind(R.id.person_have_login_layout)
    RelativeLayout personHaveLoginLayout;
    @Bind(R.id.tv_nichengjiantou)
    TextView tvNichengjiantou;
    @Bind(R.id.personal_nickname_layout)
    RelativeLayout personalNicknameLayout;
    @Bind(R.id.btn_login_out)
    TextView btnLoginOut;
    @Bind(R.id.nick_name)
    TextView nickName;
    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_xin;
    }

    @Override
    public void initview() {
//        String iconurl = getIntent().getStringExtra("iconurl");
//        String name = getIntent().getStringExtra("name");
        SharedPreferences xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
        String iconurl = xinxi.getString("iconurl", "");
        String name = xinxi.getString("name", "");
        Log.d("PersonalXinActivity", iconurl + "--------" + name);
        Glide.with(this).load(iconurl).into(ivHeadicon);
        nickName.setText(name);
    }

    @OnClick({R.id.fanhui, R.id.iv_headicon, R.id.person_have_login_layout, R.id.personal_nickname_layout, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.iv_headicon:
                break;
            case R.id.person_have_login_layout:
                break;
            case R.id.personal_nickname_layout:
                break;
            case R.id.btn_login_out:
                UMShareAPI.get(PersonalXinActivity.this).deleteOauth(PersonalXinActivity.this
                        , SHARE_MEDIA.QQ,this);
                UMShareAPI.get(PersonalXinActivity.this).deleteOauth(PersonalXinActivity.this
                        , SHARE_MEDIA.SINA,this);
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }
public static Information information;

    public static void setInformation(Information information) {
        PersonalXinActivity.information = information;
    }

    public interface Information{
    void setOnInformation();
}

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        Toast.makeText(PersonalXinActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(PersonalXinActivity.this,PersonalCenterActivity.class));
        information.setOnInformation();

        SharedPreferences xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
        SharedPreferences.Editor edit = xinxi.edit();
        edit.putBoolean("boolean",true);
        edit.commit();
        finish();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }
}
