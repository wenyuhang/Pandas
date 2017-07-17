package com.example.pandas.homes.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.model.datebean.homebean.LightChinaBean;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/13.
 */
public class LightChinaAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<LightChinaBean.ListBean> list;

    public LightChinaAdapter(Context context, ArrayList<LightChinaBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView pandaeye_newsimg;
        private TextView pandaeye_newstime,pandaeye_newstitle,pandaeye_newsdate;

        public MyViewHolder(View itemView) {
            super(itemView);
            pandaeye_newsimg= (ImageView) itemView.findViewById(R.id.pandaeye_newsimg);
            pandaeye_newstime= (TextView) itemView.findViewById(R.id.pandaeye_newstime);
            pandaeye_newstitle= (TextView) itemView.findViewById(R.id.pandaeye_newstitle);
            pandaeye_newsdate= (TextView) itemView.findViewById(R.id.pandaeye_newsdate);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pandaeye_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.pandaeye_newsimg);
        viewHolder.pandaeye_newstime.setText(list.get(position).getVideoLength());
        viewHolder.pandaeye_newstitle.setText(list.get(position).getTitle());
        viewHolder.pandaeye_newsdate.setText(list.get(position).getDaytime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
