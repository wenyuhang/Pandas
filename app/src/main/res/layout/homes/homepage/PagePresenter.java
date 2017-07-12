package com.example.apps.homes.homepage;

/**
 * Created by 联想 on 2017/7/12.
 */

public class PagePresenter implements com.example.apps.homes.homepage.PageContract.Presenter {
    private com.example.apps.homes.homepage.PageContract.View pageview;
    private com.example.apps.model.biz.IHomeModel homeModel;
    public PagePresenter(com.example.apps.homes.homepage.PageContract.View pageview) {
        this.pageview=pageview;
        this.pageview.setPresenter(this);
        homeModel=new com.example.apps.model.biz.IHomeImpl();
    }

    @Override
    public void strat() {
        homeModel.getHomePageBean(new com.example.apps.networks.mycallbacks.NetCallbacks<com.example.apps.model.datebean.PageBean>() {
            @Override
            public void onSuccess(com.example.apps.model.datebean.PageBean pageBean) {
                pageview.setResult(pageBean);
            }

            @Override
            public void onError(String errorMsg) {
                pageview.showMessage(errorMsg);
            }
        });
    }
}
