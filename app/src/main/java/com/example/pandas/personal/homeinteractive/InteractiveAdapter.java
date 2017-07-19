package com.example.pandas.personal.homeinteractive;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/14.
 */
public class InteractiveAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<InteractiveInfoBean.InteractiveBean> list;

    public InteractiveAdapter(Context context, ArrayList<InteractiveInfoBean.InteractiveBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView interactive_title;
        ImageView interactive_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            interactive_title= (TextView) itemView.findViewById(R.id.interactive_title);
            interactive_img= (ImageView) itemView.findViewById(R.id.interactive_img);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.interactive_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.interactive_img);
        viewHolder.interactive_title.setText(list.get(position).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickListener(position);
            }
        });
    }

    public ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onClickListener(int pos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
