package com.example.pandas.personal;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.weixin)
    ImageView weixin;
    @Bind(R.id.qq)
    ImageView qq;
    @Bind(R.id.sina)
    ImageView sina;
    @Bind(R.id.forget)
    TextView forget;
    @Bind(R.id.login)
    Button login;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initview() {

    }

    @OnClick({R.id.fanhui, R.id.register, R.id.weixin, R.id.qq, R.id.sina, R.id.forget, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.weixin:
                break;
            case R.id.qq:
                UMShareAPI.get(this).getPlatformInfo(this,SHARE_MEDIA.QQ,umAuthListener);
                break;
            case R.id.sina:
                break;
            case R.id.forget:
                startActivity(new Intent(LoginActivity.this,UpdateActivity.class));
                break;
            case R.id.login:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            String iconurl = data.get("iconurl");
            String name = data.get("name");
            Log.e("TAG",iconurl);
            Log.e("TAG",name);
            Intent intent = new Intent(LoginActivity.this,PersonalCenterActivity.class);
//            intent.putExtra();
//            intent.putExtra();
            startActivity(intent);
            SharedPreferences xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
            SharedPreferences.Editor edit = xinxi.edit();
            edit.putBoolean("boolean",false);
            edit.putString("iconurl",iconurl);
            edit.putString("name",name);
            edit.commit();
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


}
