package com.example.pandas.homes.personalcenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.personal.CollectionActivity;
import com.example.pandas.personal.HistoryActivity;
import com.example.pandas.personal.LoginActivity;
import com.example.pandas.personal.PersonalXinActivity;
import com.example.pandas.personal.SetActivity;
import com.example.pandas.wxapi.App;

import butterknife.Bind;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 联想 on 2017/7/22.
 */

public class PersonalMain extends BaseFragment implements PersonalXinActivity.Information,LoginActivity.Informatio {
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
    @Bind(R.id.image1)
    ImageView image;
    @Bind(R.id.title)
    TextView title;
    private boolean aBoolean;
    private String iconurl;
    private String name;

    private BroadcastReceiver bar=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            aBoolean=intent.getBooleanExtra("boolean",true);
            title.setText(intent.getStringExtra("s"));
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void init(View view) {
        IntentFilter filter=new IntentFilter("aabb");
        getActivity().registerReceiver(bar,filter);
        PersonalXinActivity.setInformation(this);
        LoginActivity.setInformatio(this);
        SharedPreferences xinxi = getActivity().getSharedPreferences("xinxi", MODE_PRIVATE);
        aBoolean = xinxi.getBoolean("boolean", true);
        iconurl = xinxi.getString("iconurl", "");
        name = xinxi.getString("name", "");

        if(aBoolean==false) {
            Glide.with(App.context).load(iconurl).into(image);
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
                Log.d("PersonalMain", "aBoolean13231:" + aBoolean);
                if (aBoolean == true) {

                    startActivity(new Intent(App.context, LoginActivity.class));
                } else {
                    Intent intent = new Intent(App.context, PersonalXinActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.personalCenter_History:
                startActivity(new Intent(App.context, HistoryActivity.class));
                break;
            case R.id.personalCenter_Collection:
                startActivity(new Intent(App.context, CollectionActivity.class));
                break;
            case R.id.personalCenter_set:
                startActivity(new Intent(App.context, SetActivity.class));
                break;
            case R.id.activity_personal_center:
                break;

        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setOnInformation() {
        aBoolean=true;
        image.setImageResource(R.mipmap.personal_login_head);
        title.setText("点击登录");
    }

    @Override
    public void setOnInfor() {
        SharedPreferences xinxi = getActivity().getSharedPreferences("xinxi", MODE_PRIVATE);
        aBoolean = xinxi.getBoolean("boolean", true);

        iconurl = xinxi.getString("iconurl", "");
        name = xinxi.getString("name", "");
        Log.d("PersonalMain", "aBoolean:" + iconurl);
        Glide.with(getActivity()).load(iconurl).into(image);
        title.setText(name);
    }

}
