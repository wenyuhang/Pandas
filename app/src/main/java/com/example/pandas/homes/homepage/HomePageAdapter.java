package com.example.pandas.homes.homepage;

import android.content.Context;
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
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;

import java.util.ArrayList;

/**
 * Created by Nicky on 2017/7/12.
 */

public class HomePageAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<HomePageBean.DataBean> list;
    private ArrayList<Object> objectList;

    private static final int LAYOUT_WONDERGUL = 1,
            LAYOUT_PANDAWATCH = 2,
            LAYOUT_PANDAEYE = 3,
            LAYOUT_PANDALIVE = 4,
            LAYOUT_GREATWALLLIVE = 5,
            LAYOUT_CHINALIVE = 6,
            LAYOUT_PLAN = 7,
            LAYOUT_CCTV = 8,
            LAYOUT_LIGHTCHINA = 9;

    private PandaEyeAdapter pandaEyeAdapter;
    private CCTVAdapter cctvAdapter;
    private LightChinaAdapter lightChinaAdapter;


    private ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> scrollList;
    private ArrayList<HomePageBean.DataBean.PandaeyeBean.ItemsBean> itemsList;
    private ArrayList<PandaEyeListBean.ListBean> pandaEyeList;
    private ArrayList<HomePageBean.DataBean.PandaliveBean.ListBean> pandaLiveList;
    private ArrayList<HomePageBean.DataBean.WallliveBean.ListBeanX> wallList;
    private ArrayList<HomePageBean.DataBean.ChinaliveBean.ListBeanXX> chinaList;
    private ArrayList<CCTVInfoBean.ListBean> cctvList;
    private ArrayList<LightChinaBean.ListBean> lightChinaList;
    private ArrayList<HomePageBean.DataBean.InteractiveBean.InteractiveoneBean> interactiveList;


    public HomePageAdapter(Context context, ArrayList<HomePageBean.DataBean> list, ArrayList<Object> objectList,
                           ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> scrollList,
                           ArrayList<HomePageBean.DataBean.PandaeyeBean.ItemsBean> itemsList,
                           ArrayList<PandaEyeListBean.ListBean> pandaEyeList,
                           ArrayList<HomePageBean.DataBean.PandaliveBean.ListBean> pandaLiveList,
                           ArrayList<HomePageBean.DataBean.WallliveBean.ListBeanX> wallList,
                           ArrayList<HomePageBean.DataBean.ChinaliveBean.ListBeanXX> chinaList,
                           ArrayList<CCTVInfoBean.ListBean> cctvList,
                           ArrayList<LightChinaBean.ListBean> lightChinaList,
                           ArrayList<HomePageBean.DataBean.InteractiveBean.InteractiveoneBean> interactiveList) {
        this.context = context;
        this.list = list;
        this.objectList=objectList;
        this.scrollList=scrollList;
        this.itemsList=itemsList;
        this.pandaEyeList=pandaEyeList;
        this.pandaLiveList=pandaLiveList;
        this.wallList=wallList;
        this.chinaList=chinaList;
        this.cctvList=cctvList;
        this.lightChinaList=lightChinaList;
        this.interactiveList=interactiveList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 1:
                View wonderfulView=LayoutInflater.from(context).inflate(R.layout.homepage_wonderful_recommendation_item,null);
                holder=new WonderfulViewHolder(wonderfulView);
                break;
            case 2:
                View pandaWatchView=LayoutInflater.from(context).inflate(R.layout.homepage_panda_watch_item,null);
                holder=new PandaWatchViewHolder(pandaWatchView);
                break;
            case 3:
                View pandaEyeView=LayoutInflater.from(context).inflate(R.layout.homepage_pandaeye_item,null);
                holder=new PandaEyeViewHolder(pandaEyeView);
                break;
            case 4:
                View pandaLiveView=LayoutInflater.from(context).inflate(R.layout.homepage_panda_live_item,null);
                holder=new PandaLiveViewHolder(pandaLiveView);
                break;
            case 5:
                View greatWallView=LayoutInflater.from(context).inflate(R.layout.homepage_panda_live_item,null);
                holder=new GreatWallLiveViewHolder(greatWallView);
                break;
            case 6:
                View chinaLiveView=LayoutInflater.from(context).inflate(R.layout.homepage_panda_live_item,null);
                holder=new LiveInChinaViewHolder(chinaLiveView);
                break;
            case 7:
                View planView=LayoutInflater.from(context).inflate(R.layout.homepage_special_planning_item,null);
                holder=new PlanViewHolder(planView);
                break;
            case 8:
                View cctvView=LayoutInflater.from(context).inflate(R.layout.homepage_panda_live_item,null);
                holder=new CCTVViewHolder(cctvView);
                break;
            case 9:
                View lightChinaView=LayoutInflater.from(context).inflate(R.layout.homepage_panda_live_item,null);
                holder=new LightChinaViewHolder(lightChinaView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case LAYOUT_WONDERGUL:
                WonderfulViewHolder wonderfulViewHolder= (WonderfulViewHolder) holder;
                initWonderful(wonderfulViewHolder);
                break;
            case LAYOUT_PANDAWATCH:
                PandaWatchViewHolder pandaWatchViewHolder= (PandaWatchViewHolder) holder;
                initPandaWatch(pandaWatchViewHolder);
                break;
            case LAYOUT_PANDAEYE:
                PandaEyeViewHolder pandaEyeViewHolder= (PandaEyeViewHolder) holder;
                initPandaEye(pandaEyeViewHolder);
                break;
            case LAYOUT_PANDALIVE:
                PandaLiveViewHolder pandaLiveViewHolder= (PandaLiveViewHolder) holder;
                initPandaLive(pandaLiveViewHolder);
                break;
            case LAYOUT_GREATWALLLIVE:
                GreatWallLiveViewHolder greatWallLiveViewHolder= (GreatWallLiveViewHolder) holder;
                initGreatWallLive(greatWallLiveViewHolder);
                break;
            case LAYOUT_CHINALIVE:
                LiveInChinaViewHolder liveInChinaViewHolder= (LiveInChinaViewHolder) holder;
                initLiveInChina(liveInChinaViewHolder);
                break;
            case LAYOUT_PLAN:
                PlanViewHolder planViewHolder= (PlanViewHolder) holder;
                initPlan(planViewHolder);
                break;
            case LAYOUT_CCTV:
                CCTVViewHolder cctvViewHolder= (CCTVViewHolder) holder;
                initCCTV(cctvViewHolder);
                break;
            case LAYOUT_LIGHTCHINA:
                LightChinaViewHolder lightChinaViewHolder= (LightChinaViewHolder) holder;
                initLightChina(lightChinaViewHolder);
                break;
        }
    }

    private void initWonderful(WonderfulViewHolder wonderfulViewHolder) {
        wonderfulViewHolder.wonderfulRecommendation.setText(list.get(0).getArea().getTitle());
        Glide.with(context).load(list.get(0).getArea().getImage()).into(wonderfulViewHolder.home_wonderful_recommendation_img);
        wonderfulViewHolder.wonderfulRecommendationRecyclerview.setHasFixedSize(true);
        wonderfulViewHolder.wonderfulRecommendationRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        WonderfulreCommendationAdapter wonderfulreCommendationAdapter=new WonderfulreCommendationAdapter(context,scrollList);
        wonderfulViewHolder.wonderfulRecommendationRecyclerview.setAdapter(wonderfulreCommendationAdapter);
        wonderfulreCommendationAdapter.setClickListener(new WonderfulreCommendationAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onWonderfulreItemClickListener(position);
            }
        });
    }

    private void initPandaWatch(PandaWatchViewHolder pandaWatchViewHolder) {
        pandaWatchViewHolder.pandaWatch.setText(list.get(0).getPandaeye().getTitle());
        Glide.with(context).load(list.get(0).getPandaeye().getPandaeyelogo()).into(pandaWatchViewHolder.pandaWatchImg);
        pandaWatchViewHolder.pandaWatchTitleRecyclerview.setHasFixedSize(true);
        pandaWatchViewHolder.pandaWatchTitleRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        PandaWatchTitleAdapter pandaWatchTitleAdapter=new PandaWatchTitleAdapter(context,itemsList);
        pandaWatchViewHolder.pandaWatchTitleRecyclerview.setAdapter(pandaWatchTitleAdapter);
        pandaWatchTitleAdapter.setClickListener(new PandaWatchTitleAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onPandaWatchItemClickListener(position);
            }
        });
    }

    private void initPandaEye(PandaEyeViewHolder pandaEyeViewHolder) {
        pandaEyeAdapter = new PandaEyeAdapter(context,pandaEyeList);
        pandaEyeViewHolder.pandaWatchNewsRecyclerview.setHasFixedSize(true);
        pandaEyeViewHolder.pandaWatchNewsRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        pandaEyeViewHolder.pandaWatchNewsRecyclerview.setAdapter(pandaEyeAdapter);
        pandaEyeAdapter.setClickListener(new PandaEyeAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onPandaEyeItemClickListener(position);
            }
        });
    }

    private void initPandaLive(PandaLiveViewHolder pandaLiveViewHolder) {
        pandaLiveViewHolder.liveTitle.setText(list.get(0).getPandalive().getTitle());
        pandaLiveViewHolder.liveRecyclerview.setHasFixedSize(true);
        pandaLiveViewHolder.liveRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        PandaLiveAdapter pandaLiveAdapter=new PandaLiveAdapter(context,pandaLiveList);
        pandaLiveViewHolder.liveRecyclerview.setAdapter(pandaLiveAdapter);
        pandaLiveAdapter.setClickListener(new PandaLiveAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onPandaLiveItemClickListener(position);
            }
        });
    }

    private void initGreatWallLive(GreatWallLiveViewHolder greatWallLiveViewHolder) {
        greatWallLiveViewHolder.liveTitle.setText(list.get(0).getWalllive().getTitle());
        greatWallLiveViewHolder.liveRecyclerview.setHasFixedSize(true);
        greatWallLiveViewHolder.liveRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        GreatWallLiveAdapter greatWallLiveAdapter=new GreatWallLiveAdapter(wallList,context);
        greatWallLiveViewHolder.liveRecyclerview.setAdapter(greatWallLiveAdapter);
        greatWallLiveAdapter.setClickListener(new GreatWallLiveAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onGreatWallLiveItemClickListener(position);
            }
        });
    }

    private void initLiveInChina(LiveInChinaViewHolder liveInChinaViewHolder) {
        liveInChinaViewHolder.liveTitle.setText(list.get(0).getChinalive().getTitle());
        liveInChinaViewHolder.liveRecyclerview.setHasFixedSize(true);
        liveInChinaViewHolder.liveRecyclerview.setLayoutManager(new GridLayoutManager(context,3));
        LiveInChinaAdapter liveInChinaAdapter=new LiveInChinaAdapter(context,chinaList);
        liveInChinaViewHolder.liveRecyclerview.setAdapter(liveInChinaAdapter);
        liveInChinaAdapter.setClickListener(new LiveInChinaAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onLiveInChinaItemClickListener(position);
            }
        });
    }

    private void initPlan(PlanViewHolder planViewHolder) {
        planViewHolder.specialPlanning.setText(list.get(0).getInteractive().getTitle());
        Glide.with(context).load(interactiveList.get(0).getImage()).into(planViewHolder.specialPlanningImg);
        planViewHolder.specialPlanningTitle.setText(interactiveList.get(0).getTitle());
        planViewHolder.specialPlanningImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onSpecialPlanItemClickListener(0);
            }
        });
    }

    private void initCCTV(CCTVViewHolder cctvViewHolder) {
        cctvViewHolder.liveTitle.setText(list.get(0).getCctv().getTitle());
        cctvViewHolder.liveRecyclerview.setHasFixedSize(true);
        cctvViewHolder.liveRecyclerview.setLayoutManager(new GridLayoutManager(context,2));
        cctvAdapter = new CCTVAdapter(context,cctvList);
        cctvViewHolder.liveRecyclerview.setAdapter(cctvAdapter);
        cctvAdapter.setClickListener(new CCTVAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onCCTVItemClickListener(position);
            }
        });
    }

    private void initLightChina(LightChinaViewHolder lightChinaViewHolder) {
        lightChinaViewHolder.liveTitle.setText(list.get(0).getList().get(0).getTitle());
        lightChinaViewHolder.liveRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        lightChinaAdapter = new LightChinaAdapter(context,lightChinaList);
        lightChinaViewHolder.liveRecyclerview.setAdapter(lightChinaAdapter);
        lightChinaAdapter.setClickListener(new LightChinaAdapter.itemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                clickListener.onLightChinaItemClickListener(position);
            }
        });
    }


    class WonderfulViewHolder extends RecyclerView.ViewHolder {
        TextView wonderfulRecommendation;
        ImageView home_wonderful_recommendation_img;
        RecyclerView wonderfulRecommendationRecyclerview;
        public WonderfulViewHolder(View itemView) {
            super(itemView);
            wonderfulRecommendation= (TextView) itemView.findViewById(R.id.wonderful_recommendation);
            wonderfulRecommendationRecyclerview= (RecyclerView) itemView.findViewById(R.id.wonderful_recommendation_recyclerview);
            home_wonderful_recommendation_img= (ImageView) itemView.findViewById(R.id.home_wonderful_recommendation_img);
        }
    }

    class PandaWatchViewHolder extends RecyclerView.ViewHolder {
        TextView pandaWatch;
        ImageView pandaWatchImg;
        RecyclerView pandaWatchTitleRecyclerview;
        public PandaWatchViewHolder(View itemView) {
            super(itemView);
            pandaWatch= (TextView) itemView.findViewById(R.id.panda_watch);
            pandaWatchImg= (ImageView) itemView.findViewById(R.id.panda_watch_img);
            pandaWatchTitleRecyclerview= (RecyclerView) itemView.findViewById(R.id.panda_watch_title_recyclerview);
        }
    }

    class PandaEyeViewHolder extends RecyclerView.ViewHolder {
        RecyclerView pandaWatchNewsRecyclerview;
        public PandaEyeViewHolder(View itemView) {
            super(itemView);
            pandaWatchNewsRecyclerview= (RecyclerView) itemView.findViewById(R.id.panda_watch_news_recyclerview);
        }
    }

    class PandaLiveViewHolder extends RecyclerView.ViewHolder {
        TextView liveTitle;
        RecyclerView liveRecyclerview;
        public PandaLiveViewHolder(View itemView) {
            super(itemView);
            liveTitle= (TextView) itemView.findViewById(R.id.live_title);
            liveRecyclerview= (RecyclerView) itemView.findViewById(R.id.live_recyclerview);
        }
    }

    class GreatWallLiveViewHolder extends RecyclerView.ViewHolder {
        TextView liveTitle;
        RecyclerView liveRecyclerview;
        public GreatWallLiveViewHolder(View itemView) {
            super(itemView);
            liveTitle= (TextView) itemView.findViewById(R.id.live_title);
            liveRecyclerview= (RecyclerView) itemView.findViewById(R.id.live_recyclerview);
        }
    }

    class LiveInChinaViewHolder extends RecyclerView.ViewHolder {
        TextView liveTitle;
        RecyclerView liveRecyclerview;
        public LiveInChinaViewHolder(View itemView) {
            super(itemView);
            liveTitle= (TextView) itemView.findViewById(R.id.live_title);
            liveRecyclerview= (RecyclerView) itemView.findViewById(R.id.live_recyclerview);
        }
    }

    class PlanViewHolder extends RecyclerView.ViewHolder {

        TextView specialPlanning;
        ImageView specialPlanningImg;
        TextView specialPlanningTitle;

        public PlanViewHolder(View itemView) {
            super(itemView);
            specialPlanning= (TextView) itemView.findViewById(R.id.special_planning);
            specialPlanningImg= (ImageView) itemView.findViewById(R.id.special_planning_img);
            specialPlanningTitle= (TextView) itemView.findViewById(R.id.special_planning_title);
        }
    }

    class CCTVViewHolder extends RecyclerView.ViewHolder {
        TextView liveTitle;
        RecyclerView liveRecyclerview;
        public CCTVViewHolder(View itemView) {
            super(itemView);
            liveTitle= (TextView) itemView.findViewById(R.id.live_title);
            liveRecyclerview= (RecyclerView) itemView.findViewById(R.id.live_recyclerview);
        }
    }

    class LightChinaViewHolder extends RecyclerView.ViewHolder {
        TextView liveTitle;
        RecyclerView liveRecyclerview;
        public LightChinaViewHolder(View itemView) {
            super(itemView);
            liveTitle= (TextView) itemView.findViewById(R.id.live_title);
            liveRecyclerview= (RecyclerView) itemView.findViewById(R.id.live_recyclerview);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object o=objectList.get(position);
        if(o instanceof HomePageBean.DataBean.AreaBean){
            return 1;
        }else if(o instanceof HomePageBean.DataBean.PandaeyeBean.ItemsBean) {
            return 2;
        }else if(o instanceof HomePageBean.DataBean.PandaeyeBean) {
            return 3;
        }else if(o instanceof HomePageBean.DataBean.PandaliveBean){
            return 4;
        }else if(o instanceof HomePageBean.DataBean.WallliveBean) {
            return 5;
        }else if(o instanceof HomePageBean.DataBean.ChinaliveBean) {
            return 6;
        }else if(o instanceof HomePageBean.DataBean.InteractiveBean) {
            return 7;
        }else if(o instanceof HomePageBean.DataBean.CctvBean) {
            return 8;
        }else if(o instanceof HomePageBean.DataBean.ListBeanXXX) {
            return 9;
        }

        return -1;
    }

    public itemClickListener clickListener;

    public void setClickListener(itemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface itemClickListener{
        void onCCTVItemClickListener(int position);
        void onGreatWallLiveItemClickListener(int position);
        void onLightChinaItemClickListener(int position);
        void onLiveInChinaItemClickListener(int position);
        void onPandaEyeItemClickListener(int position);
        void onPandaLiveItemClickListener(int position);
        void onPandaWatchItemClickListener(int position);
        void onWonderfulreItemClickListener(int position);
        void onSpecialPlanItemClickListener(int position);
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
