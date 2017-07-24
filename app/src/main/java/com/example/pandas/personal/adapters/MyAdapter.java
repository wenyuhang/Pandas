package com.example.pandas.personal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.personal.beans.HistoryBean;

import java.util.ArrayList;

/**
 * Created by li on 2017/4/24.
 */

public class MyAdapter extends RecyclerView.Adapter {
    SetonClickListener setonClickListener;
    private Context context;
    private ArrayList<HistoryBean> list;

    public MyAdapter(Context context, ArrayList<HistoryBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView timer;
        TextView title;
        TextView data;
        RadioButton radioButton;
        private SetonClickListener seton;

        public MyHolder(View itemView, SetonClickListener setonClickListener) {
            super(itemView);
            this.seton = setonClickListener;
            image = (ImageView) itemView.findViewById(R.id.image);
            timer = (TextView) itemView.findViewById(R.id.timer);
            title = (TextView) itemView.findViewById(R.id.title);
            data = (TextView) itemView.findViewById(R.id.date);
            radioButton = (RadioButton) itemView.findViewById(R.id.ration);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    seton.onClickListener(getAdapterPosition(),isChecked);
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.personal_listview_item, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        inflate.setLayoutParams(params);
        return new MyHolder(inflate,setonClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;

        Glide.with(context).load(list.get(position).getImage()).into(myHolder.image);
        myHolder.timer.setText(list.get(position).getTimer());
        myHolder.title.setText(list.get(position).getTitle());
        myHolder.data.setText(list.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void SetonClickListener(SetonClickListener setonClickListener){
        this.setonClickListener = setonClickListener;
    }


    public interface SetonClickListener{

        void onClickListener(int pos,Boolean is);

    }

}
