package com.example.pandas.personal;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.register)
    TextView register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initview() {

    }

    @OnClick({R.id.fanhui, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.register:
                break;
        }
    }
}
