package com.example.pandas.homes.homepandalive;


import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.pandas.R;
import com.example.pandas.model.datebean.pandasending.SendingBean;

import java.util.List;

public class MyAdapter extends BaseAdapter<SendingBean> {
    public MyAdapter(Context context, List<SendingBean> datas) {
        super(context, R.layout.item_text, datas);
    }

    @Override
    public void convert(ViewHolder holder, SendingBean sendingBean) {
        holder.setText(R.id.pandaLive_text,sendingBean.getLive().get(0).getTitle());
    }
}
