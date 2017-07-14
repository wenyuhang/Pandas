package com.example.pandas.homes.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.model.datebean.homebean.HomePageBean;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/13.
 */

public class WonderfulreCommendationAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> list;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;;
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.wonderful_recommendation_img);
        viewHolder.wonderful_recommendation_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
