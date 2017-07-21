package com.example.pandas.personal.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.personal.beans.HistoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2017/7/19.
 */

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<HistoryBean> list;
    private Holder holder;
    boolean sureTrue;
    OnClick onClick;
    TextView personalHyDelete;
    private int posi = 1;

    private ArrayList<Integer> lists = new ArrayList<>();

    public ListAdapter(Context context, ArrayList<HistoryBean> list, TextView personalHyDelete) {
        this.context = context;
        this.list = list;
        this.personalHyDelete = personalHyDelete;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public  interface OnClick{
        void choseClick(int pos);
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
        holder.cb.setChecked(list.get(position).isFlg());

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("TAG",isChecked+"");
                if(isChecked) {
                    if(posi ==0) {
                        posi = 1;
                    }
                    lists.add(position);
                    personalHyDelete.setText("删除"+posi);
                    posi++;
                    notifyDataSetChanged();
                    Log.e("TAG",position+"选了");
                }else {
                    if(posi == lists.size()+1) {
                        posi = lists.size()-1;
                    }else {
                        posi--;
                    }
                    lists.remove(position);
                    if(posi == 0) {
                        personalHyDelete.setText("删除");
                    }else {
                        personalHyDelete.setText("删除"+posi);

                    }
                    Log.e("TAG",position+"又不选了");

                    notifyDataSetChanged();
                }
            }
        });

        if(sureTrue) {
            Log.e("TAG","多选框显示");
            holder.cb.setVisibility(View.VISIBLE);
        }else {
            Log.e("TAG","多选框不显示");
            holder.cb.setVisibility(View.GONE);
        }

//        if(checkAll == 1) {
//                holder.cb.setChecked(true);
//        }else {
//                holder.cb.setChecked(false);
//        }

            return convertView;
    }

    public void setFlg(boolean sureTrue){
        this.sureTrue= sureTrue;
    }

    public ArrayList<Integer> getList(){
        return  lists;
    }

    class Holder{
        ImageView image;
        TextView timer;
        TextView title;
        TextView data;
        CheckBox cb;
    }

}
