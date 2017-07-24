package com.example.pandas.personal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.personal.beans.HistoryBean;

import java.util.ArrayList;

/**
 * Created by li on 2017/7/19.
 */

public class ListAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HistoryBean> list;

    public ListAdapter(Context context, ArrayList<HistoryBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView timer;
        TextView title;
        TextView date;
        RadioButton radio;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.adapterimg);
            timer = (TextView) itemView.findViewById(R.id.timer);
            title = (TextView) itemView.findViewById(R.id.adaptertitle);
            date = (TextView) itemView.findViewById(R.id.date);
            radio = (RadioButton) itemView.findViewById(R.id.ration);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.personal_listview_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder= (MyViewHolder) holder;
        if(list.get(position).isFlg()==true){
            viewHolder.radio.setVisibility(View.VISIBLE);
        }else{
            viewHolder.radio.setVisibility(View.GONE);
        }

        if(list.get(position).isFlgCheck()){
            viewHolder.radio.setChecked(true);
        }else{
            viewHolder.radio.setChecked(false);
        }
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.image);
        viewHolder.timer.setText(list.get(position).getTimer());
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.date.setText(list.get(position).getData());
        viewHolder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.radio.setChecked(true);
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.choseClick(position);
            }
        });
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public  interface OnClick{
        void choseClick(int pos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
