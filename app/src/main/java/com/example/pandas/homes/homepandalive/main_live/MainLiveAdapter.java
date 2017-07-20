package com.example.pandas.homes.homepandalive.main_live;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainLiveAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> mainLiveFragments;
    ArrayList<String> list;
    public MainLiveAdapter(FragmentManager fm, ArrayList<Fragment> mainLiveFragments, ArrayList<String> list) {
        super(fm);
        this.mainLiveFragments = mainLiveFragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mainLiveFragments.get(position);
    }

    @Override
    public int getCount() {
        return mainLiveFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
