package com.example.pandas;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.pandas.base.BaseActivity;
import com.example.pandas.utils.WelcomeAdapter;

import java.util.ArrayList;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.move_pager)
    ViewPager movePager;
    ArrayList<View> views;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    @Override
    public int getLayoutId() {
        return R.layout.welcome;
    }

    @Override
    public void initview() {
        views = new ArrayList<>();

        preferences = getSharedPreferences("welcome",MODE_PRIVATE);
        edit = preferences.edit();

        if(preferences.getBoolean("sure",false) == true) {
            Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
            startActivity(intent);
        }
//        PandaCulture pandaCulture = new PandaCulture();
//        PandaEye
        ImageView culture = new ImageView(this);
        culture.setScaleType(ImageView.ScaleType.FIT_XY);
        culture.setImageResource(R.mipmap.guide_one);

        ImageView eye = new ImageView(this);
        eye.setScaleType(ImageView.ScaleType.FIT_XY);
        eye.setImageResource(R.mipmap.guide_two);

        ImageView live = new ImageView(this);
        live.setScaleType(ImageView.ScaleType.FIT_XY);
        live.setImageResource(R.mipmap.guide_three);

        views.add(culture);
        views.add(eye);
        views.add(live);

        WelcomeAdapter adapter = new WelcomeAdapter(views);
        movePager.setAdapter(adapter);

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
                edit.putBoolean("sure",true);
                edit.commit();
                startActivity(intent);
            }
        });
    }

}
