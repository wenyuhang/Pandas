package com.example.pandas.homes.homepandalive.main_live.live_fragment;


import android.support.v4.app.FragmentActivity;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.pandas.R;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;

import java.util.ArrayList;

public class WatchChatAdapter extends BaseAdapter<WatchChatBean.DataBean.ContentBean>{

    private String floorNum,dateB;
    public WatchChatAdapter(FragmentActivity activity, ArrayList<WatchChatBean.DataBean.ContentBean> contentBeanArrayList, String floor, String date) {
        super(activity, R.layout.watch_recycler_item,contentBeanArrayList);
        this.floorNum = floor;
        this.dateB = date;
    }

    @Override
    public void convert(ViewHolder holder, WatchChatBean.DataBean.ContentBean contentBean) {
        holder.setText(R.id.watchChat_author,contentBean.getAuthor());
        holder.setText(R.id.watchChat_message,contentBean.getMessage());
        holder.setText(R.id.watchChat_date,dateB);

    }
}
