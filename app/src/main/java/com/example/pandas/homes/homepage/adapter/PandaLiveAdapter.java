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

public class PandaLiveAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HomePageBean.DataBean.PandaliveBean.ListBean> list;

    public PandaLiveAdapter(Context context, ArrayList<HomePageBean.DataBean.PandaliveBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView pandalive_img;
        private TextView pandalive_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            pandalive_img= (ImageView) itemView.findViewById(R.id.pandalive_img);
            pandalive_title= (TextView) itemView.findViewById(R.id.pandalive_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pandalive_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.pandalive_img);
        viewHolder.pandalive_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
