package com.example.pandas.homes.homerollvideo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by yan on 2017/7/13.
 */

public class HeaderViewPAdapter extends PagerAdapter {
    ArrayList<ImageView> mIvList;
    Context context;

    public HeaderViewPAdapter(ArrayList<ImageView> mIvList, Context context) {
        this.mIvList = mIvList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mIvList.size()>0?Integer.MAX_VALUE:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mIvList.size() > 0)
            container.addView(mIvList.get(position % mIvList.size()));
        return mIvList.get(position % mIvList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(mIvList.size()>0) {
            container.removeView(mIvList.get(position % mIvList.size()));
        }

    }
}
