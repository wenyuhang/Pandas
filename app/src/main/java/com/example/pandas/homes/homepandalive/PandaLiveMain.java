package com.example.pandas.homes.homepandalive;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 */

public class PandaLiveMain extends BaseFragment {

    @Bind(R.id.pandaLive_login)
    ImageView pandaLiveLogin;
    @Bind(R.id.pandaLive_tablayout)
    TabLayout pandaLiveTablayout;
    @Bind(R.id.pandaLive_viewPager)
    ViewPager pandaLiveViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandalive;
    }

    @Override
    protected void init(View view) {
//        直播
        TabLayout.Tab sending = pandaLiveTablayout.getTabAt(0);
//        精彩一刻
        TabLayout.Tab wonderfulMoment = pandaLiveTablayout.getTabAt(1);
//        当熊不让
        TabLayout.Tab bearLet = pandaLiveTablayout.getTabAt(2);
//
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.pandaLive_login)
    public void onViewClicked() {

    }
}
