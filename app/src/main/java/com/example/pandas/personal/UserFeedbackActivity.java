package com.example.pandas.personal;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class UserFeedbackActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.phone)
    RadioButton phone;
    @Bind(R.id.mailbox)
    RadioButton mailbox;
    @Bind(R.id.fram)
    FrameLayout fram;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_feedback;
    }

    @Override
    public void initview() {

    }

    @OnClick({R.id.fanhui, R.id.phone, R.id.mailbox})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.phone:
                break;
            case R.id.mailbox:
                break;
        }
    }
}
