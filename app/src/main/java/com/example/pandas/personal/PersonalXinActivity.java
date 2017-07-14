package com.example.pandas.personal;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalXinActivity extends BaseActivity {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_xin;
    }

    @Override
    public void initview() {

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
                break;
        }
    }
}
