package com.example.pandas.homes.homerollvideo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 联想 on 2017/6/22.
 */

public class PublicViewPagerAdapter extends PagerAdapter {
    private List<View> list;

    public PublicViewPagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (list.get(position) != null) {
            container.removeView(list.get(position));
        }
        container.addView(list.get(position));
        return list.get(position);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
