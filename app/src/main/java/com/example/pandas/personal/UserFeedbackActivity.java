package com.example.pandas.personal;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.personal.fragment.CommonProblemFragment;
import com.example.pandas.personal.fragment.ProblemFragment;

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
    private ProblemFragment problemFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_feedback;
    }

    @Override
    public void initview() {

        problemFragment = new ProblemFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fram, problemFragment);
        transaction.commit();

    }

    @OnClick({R.id.fanhui, R.id.phone, R.id.mailbox})
    public void onViewClicked(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.phone:
                transaction.replace(R.id.fram,problemFragment);
                transaction.commit();
                break;
            case R.id.mailbox:
                CommonProblemFragment commonProblemFragment = new CommonProblemFragment();
                transaction.replace(R.id.fram,commonProblemFragment);
                transaction.commit();
                break;
        }
    }
}
