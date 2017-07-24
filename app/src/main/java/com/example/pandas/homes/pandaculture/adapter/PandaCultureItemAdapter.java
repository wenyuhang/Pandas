package com.example.pandas.homes.pandaculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.panda_culture.RollDtialActivity;

import java.util.List;


/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaCultureItemAdapter extends BaseAdapter<PandaCultureEntity.ListBean> {
    setOnclick msetonclick;
    public PandaCultureItemAdapter(Context context, List<PandaCultureEntity.ListBean> datas) {
        super(context, R.layout.fragment_panda_culture_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaCultureEntity.ListBean listBean) {

        RelativeLayout relativeLayout = holder.getView(R.id.panda_observe_relativeLayout);
        holder.setText(R.id.panda_oculture_item_sp_time, listBean.getVideoLength())
                .setText(R.id.panda_culture_item_title, listBean.getTitle())
                .setText(R.id.panda_culture_item_time, listBean.getBrief());

        ImageView imageView = holder.getView(R.id.panda_culture_item_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(listBean.getImage()).into(imageView);

        holder.setOnclickListener(R.id.panda_observe_relativeLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listBean.getType().toString().equals("2")){
                    Intent it=new Intent(context, RollDtialActivity.class);
                    context.startActivity(it);

                }else{
                    msetonclick.setonlistener(listBean.getId(),listBean.getTitle());
                }

            }
        });

    }
    public interface setOnclick{
        void setonlistener(String pid,String title);
    }

    public void msetonlistener(setOnclick msetonclick){
        this.msetonclick=msetonclick;
    }


}