package com.example.pandas.homes.homerollvideo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.model.datebean.RollvideoBean;

import java.util.List;

/**
 * Created by yan on 2017/7/13.
 */

public class OutPullAdapter extends BaseAdapter<RollvideoBean.ListBean> {

    public OutPullAdapter(Context context, List<RollvideoBean.ListBean> datas) {
        super(context, R.layout.rellovideo_details_pullto_itemview, datas);
    }

    @Override
    public void convert(ViewHolder holder, final RollvideoBean.ListBean listBean) {
      holder.setText(R.id.rellovideo_details_item_title,listBean.getTitle());
      holder.setText(R.id.rellovideo_details_item_content,listBean.getBrief());
        ImageView imageView=holder.getView(R.id.rellovideo_details_item_image);
//        View inflate = LayoutInflater.from(context).inflate(R.layout.rellovideo_pullto_header_view, null);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(imageView!=null){
            Glide.with(context).load(listBean.getImage()).into(imageView);
        }

        holder.setOnclickListener(R.id.pullto_item_relative, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"跳转",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
