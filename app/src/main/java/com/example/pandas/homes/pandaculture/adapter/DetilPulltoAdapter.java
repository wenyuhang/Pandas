package com.example.pandas.homes.pandaculture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;

import java.util.List;

/**
 * Created by yan on 2017/7/15.
 */

public class DetilPulltoAdapter extends BaseAdapter<CCTVBaen.VideoBean> {

    setOnclick msetonclick;

    public DetilPulltoAdapter(Context context, List<CCTVBaen.VideoBean> datas) {
        super(context, R.layout.detilpullto_item, datas);
        View view= LayoutInflater.from(context).inflate(R.layout.detilpullto_item,null);
//        MyViewholder viewholder=new MyViewholder(context,view);
    }



    @Override
    public void convert(final ViewHolder holder, final CCTVBaen.VideoBean videoBean) {
        holder.setText(R.id.panda_culture_detial_item_sp_time, videoBean.getPtime())
                .setText(R.id.panda_culture_detial_item_title, videoBean.getT());
        ImageView imageView = holder.getView(R.id.panda_culture_detial_item_image);
        Glide.with(context).load(videoBean.getImg()).into(imageView);
        holder.setOnclickListener(R.id.culture_detial_relat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msetonclick.setonlistener(v,videoBean.getVid());
            }
        });
    }



    public interface setOnclick{
        void setonlistener(View v,String pid);
    }

    public void msetonlistener(setOnclick msetonclick){
        this.msetonclick=msetonclick;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }
    }


