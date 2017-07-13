package com.example.pandas.personal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalCenterActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.personalCenter_Signin)
    LinearLayout personalCenterSignin;
    @Bind(R.id.personalCenter_History)
    LinearLayout personalCenterHistory;
    @Bind(R.id.personalCenter_Collection)
    LinearLayout personalCenterCollection;
    @Bind(R.id.personalCenter_set)
    LinearLayout personalCenterSet;
    @Bind(R.id.activity_personal_center)
    LinearLayout activityPersonalCenter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_personal_center);
//        ButterKnife.bind(this);
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    public void initview() {

    }

    @OnClick({R.id.personalCenter_Signin, R.id.personalCenter_History, R.id.personalCenter_Collection, R.id.personalCenter_set, R.id.activity_personal_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personalCenter_Signin:
                startActivity(new Intent(PersonalCenterActivity.this,LoginActivity.class));
                break;
            case R.id.personalCenter_History:
                break;
            case R.id.personalCenter_Collection:
                break;
            case R.id.personalCenter_set:
                startActivity(new Intent(PersonalCenterActivity.this,SetActivity.class));
                break;
            case R.id.activity_personal_center:
                break;
        }
    }
}
