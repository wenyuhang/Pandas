package com.example.pandas.personal;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

    @OnClick({R.id.fanhui, R.id.register, R.id.weixin, R.id.qq, R.id.sina, R.id.forget, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.weixin:
                break;
            case R.id.qq:
                break;
            case R.id.sina:
                break;
            case R.id.forget:
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
                break;
            case R.id.login:
                break;
        }
    }
}
