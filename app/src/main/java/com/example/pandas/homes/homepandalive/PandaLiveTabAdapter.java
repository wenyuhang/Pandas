package com.example.pandas.homes.homepandalive;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PandaLiveTabAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> fragmentArrayList;
    String[] tab_title = {"直播","精彩一刻","当熊不让","超萌滚滚秀","熊猫档案","熊猫TOP榜","熊猫那些事","特别节目","原创新闻"};
    public PandaLiveTabAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title[position];

    }
}

