package com.example.pandas.homes.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.config.CultureSpActivity;
import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nicky on 2017/7/13.
 */
public class WonderfulreCommendationAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> list;
    private ArrayList<VideoInfoBean.VideoBean.ChaptersBean> videoList;

    public WonderfulreCommendationAdapter(Context context, ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView wonderful_recommendation_img;
        private TextView wonderful_recommendation_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            wonderful_recommendation_img= (ImageView) itemView.findViewById(R.id.wonderful_recommendation_img);
            wonderful_recommendation_title= (TextView) itemView.findViewById(R.id.wonderful_recommendation_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.wonderful_recommendation_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        videoList=new ArrayList<>();
        MyViewHolder viewHolder= (MyViewHolder) holder;;
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.wonderful_recommendation_img);
        viewHolder.wonderful_recommendation_title.setText(list.get(position).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("pid",list.get(position).getPid());
                IHomeImpl.ihttp.get("http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do", map, new NetCallbacks<VideoInfoBean>() {
                    @Override
                    public void onSuccess(final VideoInfoBean videoInfoBean) {
                        videoList.addAll(videoInfoBean.getVideo().getChapters());
                        Intent intent=new Intent(context, CultureSpActivity.class);
                        intent.putExtra("url",videoList.get(0).getUrl());
                        intent.putExtra("title",list.get(position).getTitle());
                        context.startActivity(intent);
                        videoList.clear();
                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
