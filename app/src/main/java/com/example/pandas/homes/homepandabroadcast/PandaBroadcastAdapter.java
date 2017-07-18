package com.example.pandas.homes.homepandabroadcast;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PandaBroadcastAdapter extends BaseAdapter<PdBBean.ListBean> {


    public PandaBroadcastAdapter(Context context, List<PdBBean.ListBean> datas) {
        super(context, R.layout.pandabroadcast_down_item, datas);
    }
//
    @Override
    public void convert(ViewHolder holder, final PdBBean.ListBean listBean) {
        holder.setText(R.id.pdbd_d_title,listBean.getTitle());
        long focus_date = listBean.getFocus_date();
        Date dat=new Date(focus_date);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = format.format(gc.getTime());
        holder.setText(R.id.pdbd_d_time,format1);
        ImageView imageView = holder.getView(R.id.pdbd_d_iamge);
        Glide.with(context).load(listBean.getPicurl()).into(imageView);
        holder.setOnclickListener(R.id.pulltorefersh_down, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listBean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
