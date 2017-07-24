package com.example.pandas.homes.homepage;


import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */
public class PagePresenter implements PageContract.Presenter {
    private PageContract.View pageview;
    private IHomeModel homeModel;
    public PagePresenter(PageContract.View pageview) {
        this.pageview=pageview;
        this.pageview.setPresenter(this);
        homeModel=new IHomeImpl();
    }

    @Override
    public void strat() {
        homeModel.getHomePageBean(new NetCallbacks<HomePageBean>() {
            @Override
            public void onSuccess(HomePageBean homePageBean) {
                pageview.setResult(homePageBean);
            }

            @Override
            public void onError(String errorMsg) {
                pageview.showMessage(errorMsg);
            }
        });
    }


    @Override
    public void pandaEyeResult(String url) {
        homeModel.getPandaEyeBean(url, new NetCallbacks<PandaEyeListBean>() {
            @Override
            public void onSuccess(PandaEyeListBean pandaEyeListBean) {
                pageview.setPandaEyesResult(pandaEyeListBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void cctvResult(String url) {
        homeModel.getCCTVBean(url, new NetCallbacks<CCTVInfoBean>() {
            @Override
            public void onSuccess(CCTVInfoBean cctvInfoBean) {
                pageview.setCCTVResult(cctvInfoBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void lightChinaResult(String url) {
        homeModel.getLightChinaBean(url, new NetCallbacks<LightChinaBean>() {
            @Override
            public void onSuccess(LightChinaBean lightChinaBean) {
                pageview.setLightChinaResult(lightChinaBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void rotationResult(String id) {
        homeModel.getRotationBean(id, new NetCallbacks<VideoInfoBean>() {
            @Override
            public void onSuccess(VideoInfoBean videoInfoBean) {
                pageview.setRotationResult(videoInfoBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void wonderfulResult(String id) {
        homeModel.getWonderfulBean(id, new NetCallbacks<VideoInfoBean>() {
            @Override
            public void onSuccess(VideoInfoBean videoInfoBean) {
                pageview.setWonderfulResult(videoInfoBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void itemResult(String id) {
        homeModel.getItemBean(id, new NetCallbacks<VideoInfoBean>() {
            @Override
            public void onSuccess(VideoInfoBean videoInfoBean) {
                pageview.setItemResult(videoInfoBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void pandaWatchResult(String id) {
        homeModel.getPandaWatchBean(id, new NetCallbacks<VideoInfoBean>() {
            @Override
            public void onSuccess(VideoInfoBean videoInfoBean) {
                pageview.setPandaWatchResult(videoInfoBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void lightChinaVideoInfo(String id) {
        homeModel.getLightChinaVideoInfo(id, new NetCallbacks<VideoInfoBean>() {
            @Override
            public void onSuccess(VideoInfoBean videoInfoBean) {
                pageview.setLightChinaVideoInfo(videoInfoBean);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }
}
