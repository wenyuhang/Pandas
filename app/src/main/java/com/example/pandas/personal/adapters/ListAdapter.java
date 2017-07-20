package com.example.pandas.personal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.personal.beans.HistoryBean;

import java.util.List;

/**
 * Created by li on 2017/7/19.
 */

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<HistoryBean> list;
    private Holder holder;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            holder.cb.setVisibility(View.VISIBLE);
        }
    };

    BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            holder.cb.setVisibility(View.GONE);
        }
    };

    private List<Integer> lists;
    public ListAdapter(Context context, List<HistoryBean> list,List<Integer> lists) {
        this.lists = lists;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = new Holder();
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.personal_listview_item,null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.timer = (TextView) convertView.findViewById(R.id.timer);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.data = (TextView) convertView.findViewById(R.id.data);
            holder.cb = (CheckBox) convertView.findViewById(R.id.cb);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

            Glide.with(context).load(list.get(position).getImage()).into(holder.image);
            holder.timer.setText(list.get(position).getTimer());
            holder.title.setText(list.get(position).getTitle());
            holder.data.setText(list.get(position).getData());

            IntentFilter intentFilter = new IntentFilter("bianji");
            context.registerReceiver(broadcastReceiver,intentFilter);

            IntentFilter intentFilter1 = new IntentFilter("quxiao");
            context.registerReceiver(broadcastReceiver1,intentFilter1);


        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true) {
                    Toast.makeText(context, "选中了", Toast.LENGTH_SHORT).show();
                        lists.add(position);
                }else {
                    Toast.makeText(context, "取消了", Toast.LENGTH_SHORT).show();
                    lists.remove(position);
                }
            }
        });

            return convertView;
    }

    class Holder{
        ImageView image;
        TextView timer;
        TextView title;
        TextView data;
        CheckBox cb;
    }

}
