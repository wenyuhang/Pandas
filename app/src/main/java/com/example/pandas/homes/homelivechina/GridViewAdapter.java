package com.example.pandas.homes.homelivechina;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.model.datebean.livechina.SceneryBean;

import java.util.List;

/**
 * Created by 联想 on 2017/7/16.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<SceneryBean.AlllistBean> list;
    private Context context;

    public GridViewAdapter(List<SceneryBean.AlllistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.live_china_gridview_item,null);
        TextView tv = (TextView) convertView.findViewById(R.id.grid_tv);
        tv.setText(list.get(position).getTitle());
        return convertView;
    }
}
