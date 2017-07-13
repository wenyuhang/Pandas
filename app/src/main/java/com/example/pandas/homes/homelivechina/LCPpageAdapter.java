package com.example.pandas.homes.homelivechina;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pandas.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 联想 on 2017/7/13.
 */

public class LCPpageAdapter extends FragmentPagerAdapter{
    private List<BaseFragment> list;
    private ArrayList<String> str;
    public LCPpageAdapter(FragmentManager fm,List<BaseFragment> list,ArrayList<String> str) {
        super(fm);
        this.list=list;
        this.str=str;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str.get(position);
    }
}
