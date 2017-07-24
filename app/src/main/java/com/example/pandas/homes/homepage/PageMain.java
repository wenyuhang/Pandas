package com.example.pandas.homes.homepage;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.CultureSpActivity;
import com.example.pandas.config.database.SqlUtils;
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;
import com.example.pandas.personal.homeinteractive.InteractiveInfoActivity;
import com.example.pandas.wxapi.App;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;


/**
 * Created by 联想 on 2017/7/10.
 */
public class PageMain extends BaseFragment implements PageContract.View {

    @Bind(R.id.home_xrecyclerview)
    XRecyclerView homeXrecyclerview;
    @Bind(R.id.pagemain_probar)
    ProgressBar pagemainProbar;
    @Bind(R.id.pagemain_relalayout)
    RelativeLayout pagemainRelalayout;
    private PageContract.Presenter presenter;
    private ArrayList<HomePageBean.DataBean> list;
    private ArrayList<Object> objectList;
    private HomePageAdapter homePageAdapter;
    private ArrayList<String> imgLists;
    private ArrayList<HomePageBean.DataBean.BigImgBean> bigImgList;
    private ArrayList<VideoInfoBean.VideoBean.ChaptersBean> videoList;
    private ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> scrollList;
    private ArrayList<HomePageBean.DataBean.PandaeyeBean.ItemsBean> itemsList;
    private ArrayList<PandaEyeListBean.ListBean> pandaEyeList;
    private ArrayList<HomePageBean.DataBean.PandaliveBean.ListBean> pandaLiveList;
    private ArrayList<HomePageBean.DataBean.WallliveBean.ListBeanX> wallList;
    private ArrayList<HomePageBean.DataBean.ChinaliveBean.ListBeanXX> chinaList;
    private ArrayList<CCTVInfoBean.ListBean> cctvList;
    private ArrayList<LightChinaBean.ListBean> lightChinaList;
    private ArrayList<HomePageBean.DataBean.InteractiveBean.InteractiveoneBean> interactiveList;
    private Banner banner;
    private TextView homepageTitle;
    private String duration;
    private int pos,wonderfulPosition,itemPosition,eyePosition,lightChinaPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.main_page;
    }

    @Override
    protected void init(View view) {
        pagemainRelalayout.setVisibility(View.VISIBLE);
        list = new ArrayList<>();
        objectList = new ArrayList<>();
        imgLists = new ArrayList<>();
        bigImgList = new ArrayList<>();
        videoList = new ArrayList<>();
        scrollList=new ArrayList<>();
        itemsList=new ArrayList<>();
        pandaEyeList=new ArrayList<>();
        pandaLiveList=new ArrayList<>();
        wallList=new ArrayList<>();
        chinaList=new ArrayList<>();
        cctvList=new ArrayList<>();
        lightChinaList=new ArrayList<>();
        interactiveList=new ArrayList<>();
    }

    @Override
    protected void loadData() {
        presenter.strat();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(final HomePageBean netBean) {
        objectList.add(netBean.getData().getArea());
        objectList.add(netBean.getData().getPandaeye().getItems().get(0));
        objectList.add(netBean.getData().getPandaeye());
        objectList.add(netBean.getData().getPandalive());
        objectList.add(netBean.getData().getWalllive());
        objectList.add(netBean.getData().getChinalive());
        objectList.add(netBean.getData().getInteractive());
        objectList.add(netBean.getData().getCctv());
        objectList.add(netBean.getData().getList().get(0));

        presenter.pandaEyeResult(netBean.getData().getPandaeye().getPandaeyelist());
        presenter.cctvResult(netBean.getData().getCctv().getListurl());
        presenter.lightChinaResult(netBean.getData().getList().get(0).getListUrl());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.homepage_header_item, null);
        homeXrecyclerview.addHeaderView(view);
        banner = (Banner) view.findViewById(R.id.home_banner);
        homepageTitle = (TextView) view.findViewById(R.id.homepage_title);
        bigImgList.addAll(netBean.getData().getBigImg());
        carousel(netBean);


        list.add(netBean.getData());

        scrollList.addAll(netBean.getData().getArea().getListscroll());

        itemsList.addAll(netBean.getData().getPandaeye().getItems());

        pandaLiveList.addAll(netBean.getData().getPandalive().getList());

        wallList.addAll(netBean.getData().getWalllive().getList());

        chinaList.addAll(netBean.getData().getChinalive().getList());

        interactiveList.addAll(netBean.getData().getInteractive().getInteractiveone());

        homeXrecyclerview.setHasFixedSize(true);
        homeXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        homeXrecyclerview.setLoadingMoreEnabled(false);
        homeXrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        homeXrecyclerview.setArrowImageView(R.mipmap.xlistview_arrow);
        homeXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgLists.clear();
                        carousel(netBean);

                        list.clear();
                        list.add(netBean.getData());

                        objectList.clear();
                        objectList.add(netBean.getData().getArea());
                        objectList.add(netBean.getData().getPandaeye().getItems().get(0));
                        objectList.add(netBean.getData().getPandaeye());
                        objectList.add(netBean.getData().getPandalive());
                        objectList.add(netBean.getData().getWalllive());
                        objectList.add(netBean.getData().getChinalive());
                        objectList.add(netBean.getData().getInteractive());
                        objectList.add(netBean.getData().getCctv());
                        objectList.add(netBean.getData().getList().get(0));
                        homeXrecyclerview.refreshComplete();
                        homePageAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onLoadMore() {

            }
        });

        homePageAdapter = new HomePageAdapter(getActivity(), list, objectList,scrollList,itemsList,
                pandaEyeList,pandaLiveList,wallList,chinaList,cctvList, lightChinaList,interactiveList);
        homeXrecyclerview.setAdapter(homePageAdapter);
        homePageAdapter.notifyDataSetChanged();

        homePageAdapter.setClickListener(new HomePageAdapter.itemClickListener() {
            @Override
            public void onCCTVItemClickListener(int position) {
                Toast.makeText(getActivity(), cctvList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGreatWallLiveItemClickListener(int position) {
                Toast.makeText(getActivity(), wallList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLightChinaItemClickListener(final int position) {
                lightChinaPosition=position;
                presenter.lightChinaVideoInfo(lightChinaList.get(position).getPid());
            }

            @Override
            public void onLiveInChinaItemClickListener(int position) {
                Toast.makeText(getActivity(), chinaList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPandaEyeItemClickListener(final int position) {
                eyePosition=position;
                presenter.pandaWatchResult(pandaEyeList.get(position).getPid());
            }

            @Override
            public void onPandaLiveItemClickListener(int position) {
                Toast.makeText(getActivity(), pandaLiveList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPandaWatchItemClickListener(final int position) {
                itemPosition=position;
                presenter.itemResult(itemsList.get(position).getPid());
            }

            @Override
            public void onWonderfulreItemClickListener(final int position) {
                wonderfulPosition=position;
                presenter.wonderfulResult(netBean.getData().getArea().getListscroll().get(wonderfulPosition).getPid());
            }

            @Override
            public void onSpecialPlanItemClickListener(int position) {
                Intent intent=new Intent(getActivity(), InteractiveInfoActivity.class);
                intent.putParcelableArrayListExtra("datas",interactiveList);
                intent.putExtra("poss",0);
                getActivity().startActivity(intent);
            }
        });

    }

    private void carousel(final HomePageBean netBean) {
        for (int i = 0; i < netBean.getData().getBigImg().size(); i++) {
            imgLists.add(netBean.getData().getBigImg().get(i).getImage());
        }
        banner.setImageLoader(new ImageLoaders());
        banner.isAutoPlay(true);
        banner.setDelayTime(2000);
        banner.setImages(imgLists).setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(final int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), InteractiveInfoActivity.class);
                    intent.putParcelableArrayListExtra("bigImgList", bigImgList);
                    intent.putExtra("position", position);
                    startActivity(intent);
                } else {
                    pos = position;
                    presenter.rotationResult(netBean.getData().getBigImg().get(position).getPid());
                }
            }
        });

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position <= 4 && position != 0) {
                    homepageTitle.setText(list.get(0).getBigImg().get(position - 1).getTitle());
                } else if (position <= 4 && position <= 0) {
                    position = 0;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPandaEyesResult(PandaEyeListBean pandaEyeListBean) {
        if(pandaEyeListBean!=null){
            pagemainRelalayout.setVisibility(View.GONE);
        }
        pandaEyeList.clear();
        pandaEyeList.addAll(pandaEyeListBean.getList());
    }

    @Override
    public void setCCTVResult(CCTVInfoBean cctvInfoBean) {
        cctvList.clear();
        cctvList.addAll(cctvInfoBean.getList());
    }

    @Override
    public void setLightChinaResult(LightChinaBean lightChinaBean) {
        lightChinaList.clear();
        lightChinaList.addAll(lightChinaBean.getList());
    }

    @Override
    public void setRotationResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());

        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

        Intent intent = new Intent(getActivity(), CultureSpActivity.class);
        intent.putExtra("url", videoList.get(0).getUrl());
        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
        intent.putExtra("imgurl",list.get(0).getBigImg().get(pos).getImage());
        intent.putExtra("movietime",duration);
        intent.putExtra("title", list.get(0).getBigImg().get(pos).getTitle());

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());

        SqlUtils.getInstance()
                .add(0,videoList.get(0).getUrl(),buffer.toString(),list.get(0).getBigImg().get(pos).getTitle(),date,list.get(0).getBigImg().get(pos).getImage());
        startActivity(intent);
        videoList.clear();
    }

    @Override
    public void setWonderfulResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());

        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

        Intent intent=new Intent(getActivity(), CultureSpActivity.class);
        intent.putExtra("url",videoList.get(0).getUrl());
        intent.putExtra("title",scrollList.get(wonderfulPosition).getTitle());
        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
        intent.putExtra("imgurl",scrollList.get(wonderfulPosition).getImage());
        intent.putExtra("movietime",duration);

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());

        SqlUtils.getInstance()
                .add(0,videoList.get(0).getUrl(),buffer.toString(),scrollList.get(wonderfulPosition).getTitle(),date,scrollList.get(wonderfulPosition).getImage());
        getActivity().startActivity(intent);
        videoList.clear();
    }

    @Override
    public void setItemResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());

        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

        Intent intent=new Intent(getActivity(), CultureSpActivity.class);
        intent.putExtra("url",videoList.get(0).getUrl());
        intent.putExtra("title",itemsList.get(itemPosition).getTitle());
        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
        intent.putExtra("imgurl","");
        intent.putExtra("movietime",duration);


        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());

        SqlUtils.getInstance()
                .add(0,videoList.get(0).getUrl(),buffer.toString(),itemsList.get(itemPosition).getTitle(),date,itemsList.get(itemPosition).getUrl());
        getActivity().startActivity(intent);
        videoList.clear();
    }

    @Override
    public void setPandaWatchResult(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());

        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

        Intent intent=new Intent(getActivity(), CultureSpActivity.class);
        intent.putExtra("url",videoList.get(0).getUrl());
        intent.putExtra("title",pandaEyeList.get(eyePosition).getTitle());
        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
        intent.putExtra("imgurl",pandaEyeList.get(eyePosition).getImage());
        intent.putExtra("movietime",duration);

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());

        SqlUtils.getInstance()
                .add(0,videoList.get(0).getUrl(),buffer.toString(),pandaEyeList.get(eyePosition).getTitle(),date,pandaEyeList.get(eyePosition).getImage());
        getActivity().startActivity(intent);
        videoList.clear();
    }

    @Override
    public void setLightChinaVideoInfo(VideoInfoBean videoInfoBean) {
        videoList.addAll(videoInfoBean.getVideo().getChapters());

        duration=videoList.get(0).getDuration();
        StringBuffer buffer=new StringBuffer();
        if(Integer.valueOf(duration)<60){
            buffer.append("00:").append(duration);
        }else{
            duration=(Integer.valueOf(duration)%60)+"";
            buffer.append("01:").append(duration);
        }

        Intent intent=new Intent(getActivity(), CultureSpActivity.class);
        intent.putExtra("url",videoList.get(0).getUrl());
        intent.putExtra("title",lightChinaList.get(lightChinaPosition).getTitle());
        intent.putExtra("otherurl",videoInfoBean.getVideo().getLowChapters().get(0).getUrl());
        intent.putExtra("imgurl",lightChinaList.get(lightChinaPosition).getImage());
        intent.putExtra("movietime",duration);

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());

        SqlUtils.getInstance()
                .add(0,videoList.get(0).getUrl(),buffer.toString(),lightChinaList.get(lightChinaPosition).getTitle(),date,lightChinaList.get(lightChinaPosition).getImage());
        getActivity().startActivity(intent);
        videoList.clear();
    }

    @Override
    public void setPresenter(PageContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
