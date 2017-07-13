package com.example.pandas.personal;

import android.widget.ImageButton;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

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
        finish();
    }
}
