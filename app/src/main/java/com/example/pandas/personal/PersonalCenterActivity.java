package com.example.pandas.personal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.title)
    TextView title;
    private boolean aBoolean;
    private String iconurl;
    private String name;


    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    public void initview() {
        SharedPreferences xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
        aBoolean = xinxi.getBoolean("boolean", true);
        iconurl = xinxi.getString("iconurl", "");
        name = xinxi.getString("name", "");

        if(aBoolean==false) {
            Glide.with(PersonalCenterActivity.this).load(iconurl).into(image);
            title.setText(name);
        }else {
            image.setImageResource(R.mipmap.personal_login_head);
            title.setText("点击登录");
        }
    }

    @OnClick({R.id.personalCenter_Signin, R.id.personalCenter_History, R.id.personalCenter_Collection, R.id.personalCenter_set, R.id.activity_personal_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personalCenter_Signin:
                if (aBoolean == true) {
                    startActivity(new Intent(PersonalCenterActivity.this, LoginActivity.class));
//                    image.setImageResource(R.mipmap.personal_login_head);
//                    title.setText("点击登录");
                } else {
                    Intent intent = new Intent(PersonalCenterActivity.this, PersonalXinActivity.class);
//                    intent.putExtra("iconurl",iconurl);
//                    intent.putExtra("name",);
                    startActivity(intent);
//                    Glide.with(PersonalCenterActivity.this).load(iconurl).into(image);
//                    title.setText(name);
                }
                break;
            case R.id.personalCenter_History:
                startActivity(new Intent(PersonalCenterActivity.this, HistoryActivity.class));
                break;
            case R.id.personalCenter_Collection:
                startActivity(new Intent(PersonalCenterActivity.this, CollectionActivity.class));
                break;
            case R.id.personalCenter_set:
                startActivity(new Intent(PersonalCenterActivity.this, SetActivity.class));
                break;
            case R.id.activity_personal_center:
                break;
            case R.id.fanhui:
                finish();
                break;
        }
    }
}