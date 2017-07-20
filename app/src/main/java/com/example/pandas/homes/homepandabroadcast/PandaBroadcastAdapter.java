package com.example.pandas.homes.homepandabroadcast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.config.CultureSpActivity;
import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.datebean.pandabroadcastbean.BandaBroadBean;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PandaBroadcastAdapter extends BaseAdapter<PdBBean.ListBean> {


    public PandaBroadcastAdapter(Context context, List<PdBBean.ListBean> datas) {
        super(context, R.layout.pandabroadcast_down_item, datas);
    }

    @Override
    public void convert(final ViewHolder holder, final PdBBean.ListBean pdBBean) {
        holder.setText(R.id.pdbd_d_title, pdBBean.getTitle());
        long focus_date = pdBBean.getFocus_date();
        Date dat = new Date(focus_date);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = format.format(gc.getTime());
        holder.setText(R.id.pdbd_d_time, format1);
        ImageView imageView = holder.getView(R.id.pdbd_d_iamge);
        Glide.with(context).load(pdBBean.getPicurl()).into(imageView);
        holder.setOnclickListener(R.id.pulltorefersh_down, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guid = pdBBean.getGuid();
                Map<String, String> map = new HashMap<String, String>();
                map.put("pid", guid);
                IHomeImpl.ihttp.get("http://115.182.35.91/api/getVideoInfoForCBox.do", map, new NetCallbacks<BandaBroadBean>() {

                    @Override
                    public void onSuccess(BandaBroadBean bandaBroadBean) {
                        // 标题
                        String title = bandaBroadBean.getTitle();

                        //高清
                        String url = bandaBroadBean.getVideo().getChapters().get(0).getUrl();
                        Log.e("高清", url);
                        //标清
                        String url1 = bandaBroadBean.getVideo().getLowChapters().get(0).getUrl();
                        Log.e("标清", url1);

                        SharedPreferences s1 = context.getSharedPreferences("s1", Context.MODE_PRIVATE);
                        if(s1.getBoolean("bool",false)){
                            Log.e("123","----------");
                        }else {
                            Intent intent = new Intent(context, CultureSpActivity.class);
                            intent.putExtra("url",url);
                            intent.putExtra("title",title);
                            context.startActivity(intent);
                            SharedPreferences.Editor edit = s1.edit();
                            edit.putBoolean("bool",true);
                            edit.commit();
                        }




                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });
            }
        });

    }

}
