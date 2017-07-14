package com.example.pandas.homes.homerollvideo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.pandas.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 联想 on 2017/6/22.
 */

public class ViewPagerUtils implements ViewPager.OnPageChangeListener{
    private static ViewPagerUtils utils;
    private List<View> list;
    private String[] ints;
    private RelativeLayout linlayout;
    private Context con;

    public ViewPagerUtils() {

    }

    public static  synchronized ViewPagerUtils getIntance(){
        if(utils==null){

            utils = new ViewPagerUtils();
        }


       return utils;
    }

    public void setpager(Context context, final String[] in, final RelativeLayout relativeLayout, ViewPager viewPager){
        list = new ArrayList<View>();
        ints= in;
        Log.d("TAG","list____"+list.size()+"ints-----"+ints.length);
        con = context;
        linlayout = relativeLayout;
        for (int i = 0; i <in.length ; i++) {
            //存储七个imageview对象
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(in[i])
//                    .skipMemoryCache(true)
//                    .thumbnail(0.05f)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(360,200)
                    .into(imageView);
            list.add(imageView);
            View view = new View(context);
            view.setBackgroundResource(R.drawable.point_normal);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(20,20);
            params.setMargins(10,0,0,0);
            relativeLayout.addView(view,params);
        }
        relativeLayout.getChildAt(0).setBackgroundResource(R.drawable.point_selected);
        PublicViewPagerAdapter homePagePagerAdapter = new PublicViewPagerAdapter(list);
        viewPager.setAdapter(homePagePagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    //页面滑动的时候调用（一直被调用）
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    //页面被选中的时候调用（关联下面的几个点）
    @Override
    public void onPageSelected(int position) {
        int newPosition= position%list.size();
        for(int i = 0; i <ints.length ; i++) {
            if(i==newPosition){
                //如果相等 就将i对应的点设置为选中状态，其他点都是设置成未选中状态
                linlayout.getChildAt(i).setBackgroundResource(R.drawable.point_selected);
            }else{
                linlayout.getChildAt(i).setBackgroundResource(R.drawable.point_normal);
            }
        }
    }

    //页面滑动状态发生改变的时候调用   停止滑动 开始滑动
    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
