package com.example.pandas.homes.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.model.datebean.homebean.HomePageBean;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/13.
 */
public class PandaWatchTitleAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HomePageBean.DataBean.PandaeyeBean.ItemsBean> list;

    public PandaWatchTitleAdapter(Context context, ArrayList<HomePageBean.DataBean.PandaeyeBean.ItemsBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private Button panda_watch_btn;
        private TextView panda_watch_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            panda_watch_btn= (Button) itemView.findViewById(R.id.panda_watch_btn);
            panda_watch_title= (TextView) itemView.findViewById(R.id.panda_watch_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pandawatchtitle_adapter_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        viewHolder.panda_watch_btn.setText(list.get(position).getBrief());
        viewHolder.panda_watch_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

