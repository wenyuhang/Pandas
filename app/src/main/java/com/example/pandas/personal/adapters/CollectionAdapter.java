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
import com.example.pandas.config.CollectionDate;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/24.
 */

public class CollectionAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<CollectionDate> list;

    public CollectionAdapter(Context context, ArrayList<CollectionDate> list) {
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
            image= (ImageView) itemView.findViewById(R.id.collection_img);
            timer = (TextView) itemView.findViewById(R.id.collection_timer);
            title = (TextView) itemView.findViewById(R.id.collection_title);
            date = (TextView) itemView.findViewById(R.id.collection_date);
            radio= (RadioButton) itemView.findViewById(R.id.collection_ration);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.collection_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImageurl()).into(viewHolder.image);
        viewHolder.title.setText(list.get(position).getMoviename());
        viewHolder.timer.setText(list.get(position).getMovietime());
        viewHolder.date.setText(list.get(position).getMoviedate());

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
