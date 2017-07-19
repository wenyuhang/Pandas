package com.example.pandas.homes.homepage;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.CultureSpActivity;
import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;
import com.example.pandas.personal.PersonalCenterActivity;
import com.example.pandas.personal.homeinteractive.InteractiveInfoActivity;
import com.example.pandas.personal.homeinteractive.InteractiveMainActivity;
import com.example.pandas.wxapi.App;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 */
public class PageMain extends BaseFragment implements PageContract.View {
    @Bind(R.id.home_logo_img)
    ImageView homeLogoImg;
    @Bind(R.id.home_personal)
    ImageView homePersonal;
    @Bind(R.id.home_interactive)
    ImageView homeInteractive;
    @Bind(R.id.home_xrecyclerview)
    XRecyclerView homeXrecyclerview;
    private PageContract.Presenter presenter;
    private ArrayList<HomePageBean.DataBean> list;
    private ArrayList<Object> objectList;
    private HomePageAdapter homePageAdapter;
    private ArrayList<String> imgLists=new ArrayList<>();
    private ArrayList<HomePageBean.DataBean.BigImgBean> bigImgList;
    private ArrayList<VideoInfoBean.VideoBean.ChaptersBean> videoList;
    private Banner banner;
    private TextView homepageTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.main_page;
    }

    @Override
    protected void init(View view) {
        list=new ArrayList<>();
        objectList=new ArrayList<>();
        imgLists=new ArrayList<>();
        bigImgList=new ArrayList<>();
        videoList=new ArrayList<>();
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

        View view= LayoutInflater.from(getActivity()).inflate(R.layout.homepage_header_item,null);
        homeXrecyclerview.addHeaderView(view);
        banner = (Banner) view.findViewById(R.id.home_banner);
        homepageTitle = (TextView) view.findViewById(R.id.homepage_title);
        bigImgList.addAll(netBean.getData().getBigImg());
        carousel(netBean);


        list.add(netBean.getData());
        homeXrecyclerview.setHasFixedSize(true);
        homeXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
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

        homePageAdapter = new HomePageAdapter(getActivity(),list,objectList);
        homeXrecyclerview.setAdapter(homePageAdapter);

    }

    private void carousel(final HomePageBean netBean) {
        for(int i=0;i<netBean.getData().getBigImg().size();i++){
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
                if(position==0){
                    Intent intent=new Intent(getActivity(), InteractiveInfoActivity.class);
                    intent.putParcelableArrayListExtra("bigImgList",bigImgList);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }else{
                    HashMap<String,String> map=new HashMap<String, String>();
                    map.put("pid",netBean.getData().getBigImg().get(position).getPid());
                    IHomeImpl.ihttp.get("http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do", map, new NetCallbacks<VideoInfoBean>() {
                        @Override
                        public void onSuccess(final VideoInfoBean videoInfoBean) {
                            videoList.addAll(videoInfoBean.getVideo().getChapters());
                            Intent intent=new Intent(getActivity(), CultureSpActivity.class);
                            intent.putExtra("url",videoList.get(0).getUrl());
                            intent.putExtra("title",list.get(0).getBigImg().get(position).getTitle());
                            startActivity(intent);
                            videoList.clear();
                        }

                        @Override
                        public void onError(String errorMsg) {

                        }
                    });
                }
            }
        });

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position<=4 && position!=0){
                    homepageTitle.setText(list.get(0).getBigImg().get(position-1).getTitle());
                }else if(position<=4 && position<=0){
                    position=0;
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
    public void setPresenter(PageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick({R.id.home_personal, R.id.home_interactive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_personal:
                startActivity(new Intent(getActivity(), PersonalCenterActivity.class));
                break;
            case R.id.home_interactive:
                startActivity(new Intent(getActivity(), InteractiveMainActivity.class));
                break;

        }
    }
}
