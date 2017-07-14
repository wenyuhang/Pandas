package com.example.pandas.homes.homepage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.homes.homepage.adapter.CCTVAdapter;
import com.example.pandas.homes.homepage.adapter.GreatWallLiveAdapter;
import com.example.pandas.homes.homepage.adapter.LightChinaAdapter;
import com.example.pandas.homes.homepage.adapter.LiveInChinaAdapter;
import com.example.pandas.homes.homepage.adapter.PandaEyeAdapter;
import com.example.pandas.homes.homepage.adapter.PandaLiveAdapter;
import com.example.pandas.homes.homepage.adapter.PandaWatchTitleAdapter;
import com.example.pandas.homes.homepage.adapter.WonderfulreCommendationAdapter;
import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import static com.example.pandas.R.id.panda_watch_title_recyclerview;

/**
 * Created by Nicky on 2017/7/12.
 */

public class HomePageAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HomePageBean.DataBean> list;
    private PandaEyeAdapter pandaEyeAdapter;
    private CCTVAdapter cctvAdapter;
    private LightChinaAdapter lightChinaAdapter;


    private ArrayList<String> imgLists=new ArrayList<>();
    private ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> scrollList=new ArrayList<>();
    private ArrayList<HomePageBean.DataBean.PandaeyeBean.ItemsBean> itemsList=new ArrayList<>();
    private ArrayList<PandaEyeListBean.ListBean> pandaEyeList=new ArrayList<>();
    private ArrayList<HomePageBean.DataBean.PandaliveBean.ListBean> pandaLiveList=new ArrayList<>();
    private ArrayList<HomePageBean.DataBean.WallliveBean.ListBeanX> wallList=new ArrayList<>();
    private ArrayList<HomePageBean.DataBean.ChinaliveBean.ListBeanXX> chinaList=new ArrayList<>();
    private ArrayList<CCTVInfoBean.ListBean> cctvList=new ArrayList<>();
    private ArrayList<LightChinaBean.ListBean> lightChinaList=new ArrayList<>();


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 99 :
                    pandaEyeAdapter.notifyDataSetChanged();
                    break;
                case 66 :
                    cctvAdapter.notifyDataSetChanged();
                    break;
                case 77 :
                    lightChinaAdapter.notifyDataSetChanged();
                    break;
            }

        }
    };


    public HomePageAdapter(Context context, ArrayList<HomePageBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        Banner homeBanner;
        TextView homepageTitle;
        TextView wonderfulRecommendation;
        RecyclerView wonderfulRecommendationRecyclerview;
        TextView pandaWatch;
        ImageView pandaWatchImg;
        RecyclerView pandaWatchTitleRecyclerview;
        RecyclerView pandaWatchNewsRecyclerview;
        TextView pandaLive;
        RecyclerView pandaLiveRecyclerview;
        TextView greatWallLive;
        RecyclerView greatWallLiveRecyclerview;
        TextView liveInChina;
        RecyclerView liveInChinaRecyclerview;
        TextView specialPlanning;
        ImageView specialPlanningImg;
        TextView specialPlanningTitle;
        TextView cctv;
        RecyclerView cctvRecyclerview;
        TextView lightAndShadowInChina;
        RecyclerView lightAndShadowInChinaRecyclerview;
        ImageView home_wonderful_recommendation_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            homeBanner= (Banner) itemView.findViewById(R.id.home_banner);
            homepageTitle= (TextView) itemView.findViewById(R.id.homepage_title);
            wonderfulRecommendation= (TextView) itemView.findViewById(R.id.wonderful_recommendation);
            wonderfulRecommendationRecyclerview= (RecyclerView) itemView.findViewById(R.id.wonderful_recommendation_recyclerview);
            pandaWatch= (TextView) itemView.findViewById(R.id.panda_watch);
            pandaWatchImg= (ImageView) itemView.findViewById(R.id.panda_watch_img);
            pandaWatchTitleRecyclerview= (RecyclerView) itemView.findViewById(panda_watch_title_recyclerview);
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
            home_wonderful_recommendation_img= (ImageView) itemView.findViewById(R.id.home_wonderful_recommendation_img);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_adapter_item, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder= (MyViewHolder) holder;
        imgLists.clear();
        for(int i=0;i<list.get(0).getBigImg().size();i++){
            imgLists.add(list.get(0).getBigImg().get(i).getImage());
        }
        viewHolder.homeBanner.setImageLoader(new ImageLoaders());
        viewHolder.homeBanner.isAutoPlay(true);
        viewHolder.homeBanner.setDelayTime(2000);
        viewHolder.homeBanner.setImages(imgLists).setIndicatorGravity(BannerConfig.RIGHT);
        viewHolder.homeBanner.start();
        viewHolder.homeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        viewHolder.homeBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position<=4){
                    viewHolder.homepageTitle.setText(list.get(0).getBigImg().get(position-1).getTitle());
                }else{
                    position=1;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        scrollList.clear();
        scrollList.addAll(list.get(position).getArea().getListscroll());
        viewHolder.wonderfulRecommendation.setText(list.get(position).getArea().getTitle());
        Glide.with(context).load(list.get(position).getArea().getImage()).into(viewHolder.home_wonderful_recommendation_img);
        viewHolder.wonderfulRecommendationRecyclerview.setHasFixedSize(true);
        viewHolder.wonderfulRecommendationRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        WonderfulreCommendationAdapter wonderfulreCommendationAdapter=new WonderfulreCommendationAdapter(context,scrollList);
        viewHolder.wonderfulRecommendationRecyclerview.setAdapter(wonderfulreCommendationAdapter);

        itemsList.addAll(list.get(position).getPandaeye().getItems());
        viewHolder.pandaWatch.setText(list.get(position).getPandaeye().getTitle());
        Glide.with(context).load(list.get(position).getPandaeye().getPandaeyelogo()).into(viewHolder.pandaWatchImg);
        viewHolder.pandaWatchTitleRecyclerview.setHasFixedSize(true);
        viewHolder.pandaWatchTitleRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        PandaWatchTitleAdapter pandaWatchTitleAdapter=new PandaWatchTitleAdapter(context,itemsList);
        viewHolder.pandaWatchTitleRecyclerview.setAdapter(pandaWatchTitleAdapter);

        IHomeImpl.ihttp.get(list.get(position).getPandaeye().getPandaeyelist(), null, new NetCallbacks<PandaEyeListBean>() {
            @Override
            public void onSuccess(PandaEyeListBean pandaEyeListBean) {
                pandaEyeList.clear();
                pandaEyeList.addAll(pandaEyeListBean.getList());
                handler.sendEmptyMessage(99);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

        pandaEyeAdapter = new PandaEyeAdapter(context,pandaEyeList);
        viewHolder.pandaWatchNewsRecyclerview.setHasFixedSize(true);
        viewHolder.pandaWatchNewsRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        viewHolder.pandaWatchNewsRecyclerview.setAdapter(pandaEyeAdapter);

        pandaLiveList.clear();
        pandaLiveList.addAll(list.get(position).getPandalive().getList());
        viewHolder.pandaLive.setText(list.get(position).getPandalive().getTitle());
        viewHolder.pandaLiveRecyclerview.setHasFixedSize(true);
        viewHolder.pandaLiveRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        PandaLiveAdapter pandaLiveAdapter=new PandaLiveAdapter(context,pandaLiveList);
        viewHolder.pandaLiveRecyclerview.setAdapter(pandaLiveAdapter);

        wallList.clear();
        wallList.addAll(list.get(position).getWalllive().getList());
        viewHolder.greatWallLive.setText(list.get(position).getWalllive().getTitle());;
        viewHolder.greatWallLiveRecyclerview.setHasFixedSize(true);
        viewHolder.greatWallLiveRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        GreatWallLiveAdapter greatWallLiveAdapter=new GreatWallLiveAdapter(wallList,context);
        viewHolder.greatWallLiveRecyclerview.setAdapter(greatWallLiveAdapter);

        chinaList.clear();
        chinaList.addAll(list.get(position).getChinalive().getList());
        viewHolder.liveInChina.setText(list.get(position).getChinalive().getTitle());
        viewHolder.liveInChinaRecyclerview.setHasFixedSize(true);
        viewHolder.liveInChinaRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        LiveInChinaAdapter liveInChinaAdapter=new LiveInChinaAdapter(context,chinaList);
        viewHolder.liveInChinaRecyclerview.setAdapter(liveInChinaAdapter);

        viewHolder.specialPlanning.setText(list.get(position).getInteractive().getTitle());
        Glide.with(context).load(list.get(position).getInteractive().getInteractiveone().get(position).getImage()).into(viewHolder.specialPlanningImg);
        viewHolder.specialPlanningTitle.setText(list.get(position).getInteractive().getInteractiveone().get(position).getTitle());

        viewHolder.cctv.setText(list.get(position).getCctv().getTitle());
        IHomeImpl.ihttp.get(list.get(position).getCctv().getListurl(), null, new NetCallbacks<CCTVInfoBean>() {
            @Override
            public void onSuccess(CCTVInfoBean cctvInfoBean) {
                cctvList.clear();
                cctvList.addAll(cctvInfoBean.getList());
                handler.sendEmptyMessage(66);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
        viewHolder.cctvRecyclerview.setHasFixedSize(true);
        viewHolder.cctvRecyclerview.setLayoutManager(new GridLayoutManager(context,2));
        cctvAdapter = new CCTVAdapter(context,cctvList);
        viewHolder.cctvRecyclerview.setAdapter(cctvAdapter);

        viewHolder.lightAndShadowInChina.setText(list.get(position).getList().get(position).getTitle());
        IHomeImpl.ihttp.get(list.get(position).getList().get(position).getListUrl(), null, new NetCallbacks<LightChinaBean>() {
            @Override
            public void onSuccess(LightChinaBean lightChinaBean) {
                lightChinaList.clear();
                lightChinaList.addAll(lightChinaBean.getList());
                handler.sendEmptyMessage(77);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
        viewHolder.lightAndShadowInChinaRecyclerview.setHasFixedSize(true);
        viewHolder.lightAndShadowInChinaRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        lightChinaAdapter = new LightChinaAdapter(context,lightChinaList);
        viewHolder.lightAndShadowInChinaRecyclerview.setAdapter(lightChinaAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
