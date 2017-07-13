package com.example.pandas.homes.homepage;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.model.datebean.HomePageBean;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Nicky on 2017/7/12.
 */

public class HomePageAdapter extends RecyclerView.Adapter {


    private Context context;
    private ArrayList<HomePageBean.DataBean> list;
    private ArrayList<String> imgLists=new ArrayList<>();

    public HomePageAdapter(Context context, ArrayList<HomePageBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.home_banner)
        Banner homeBanner;
        @Bind(R.id.homepage_title)
        TextView homepageTitle;
        @Bind(R.id.wonderful_recommendation)
        TextView wonderfulRecommendation;
        @Bind(R.id.wonderful_recommendation_recyclerview)
        RecyclerView wonderfulRecommendationRecyclerview;
        @Bind(R.id.panda_watch)
        TextView pandaWatch;
        @Bind(R.id.panda_watch_img)
        ImageView pandaWatchImg;
        @Bind(R.id.panda_watch_title_recyclerview)
        RecyclerView pandaWatchTitleRecyclerview;
        @Bind(R.id.panda_watch_news_recyclerview)
        RecyclerView pandaWatchNewsRecyclerview;
        @Bind(R.id.panda_live)
        TextView pandaLive;
        @Bind(R.id.panda_live_recyclerview)
        RecyclerView pandaLiveRecyclerview;
        @Bind(R.id.great_wall_Live)
        TextView greatWallLive;
        @Bind(R.id.great_wall_live_recyclerview)
        RecyclerView greatWallLiveRecyclerview;
        @Bind(R.id.live_in_china)
        TextView liveInChina;
        @Bind(R.id.live_in_china_recyclerview)
        RecyclerView liveInChinaRecyclerview;
        @Bind(R.id.special_planning)
        TextView specialPlanning;
        @Bind(R.id.special_planning_img)
        ImageView specialPlanningImg;
        @Bind(R.id.special_planning_title)
        TextView specialPlanningTitle;
        @Bind(R.id.cctv)
        TextView cctv;
        @Bind(R.id.cctv_recyclerview)
        RecyclerView cctvRecyclerview;
        @Bind(R.id.light_and_shadow_in_china)
        TextView lightAndShadowInChina;
        @Bind(R.id.light_and_shadow_in_china_recyclerview)
        RecyclerView lightAndShadowInChinaRecyclerview;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Activity) context);
            homeBanner= (Banner) itemView.findViewById(R.id.home_banner);
            homepageTitle= (TextView) itemView.findViewById(R.id.homepage_title);
            wonderfulRecommendation= (TextView) itemView.findViewById(R.id.wonderful_recommendation);
            wonderfulRecommendationRecyclerview= (RecyclerView) itemView.findViewById(R.id.wonderful_recommendation_recyclerview);
            pandaWatch= (TextView) itemView.findViewById(R.id.panda_watch);
            pandaWatchImg= (ImageView) itemView.findViewById(R.id.panda_watch_img);
            pandaWatchTitleRecyclerview= (RecyclerView) itemView.findViewById(R.id.panda_watch_title_recyclerview);
            pandaWatchNewsRecyclerview= (RecyclerView) itemView.findViewById(R.id.panda_watch_news_recyclerview);
            pandaLive= (TextView) itemView.findViewById(R.id.panda_live);
            pandaLiveRecyclerview= (RecyclerView) itemView.findViewById(R.id.panda_live_recyclerview);
            greatWallLive= (TextView) itemView.findViewById(R.id.great_wall_Live);
            greatWallLiveRecyclerview= (RecyclerView) itemView.findViewById(R.id.great_wall_live_recyclerview);
            liveInChina= (TextView) itemView.findViewById(R.id.live_in_china);
            liveInChinaRecyclerview= (RecyclerView) itemView.findViewById(R.id.live_in_china_recyclerview);
            specialPlanning= (TextView) itemView.findViewById(R.id.special_planning);
            specialPlanningImg= (ImageView) itemView.findViewById(R.id.special_planning_img);
            specialPlanningTitle= (TextView) itemView.findViewById(R.id.special_planning_title);
            cctv= (TextView) itemView.findViewById(R.id.cctv);
            cctvRecyclerview= (RecyclerView) itemView.findViewById(R.id.cctv_recyclerview);
            lightAndShadowInChina= (TextView) itemView.findViewById(R.id.light_and_shadow_in_china);
            lightAndShadowInChinaRecyclerview= (RecyclerView) itemView.findViewById(R.id.light_and_shadow_in_china_recyclerview);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_adapter_item, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        for(int i=0;i<list.get(0).getBigImg().size();i++){
            imgLists.add(list.get(0).getBigImg().get(i).getImage());
        }
        viewHolder.homeBanner.setImageLoader(new ImageLoaders());
        viewHolder.homeBanner.isAutoPlay(true);
        viewHolder.homeBanner.setDelayTime(2000);
        viewHolder.homeBanner.setImages(imgLists);
        viewHolder.homeBanner.start();
        viewHolder.homepageTitle.setText(list.get(0).getBigImg().get(position).getTitle());

        viewHolder.wonderfulRecommendation.setText(list.get(position).getArea().getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
