package com.example.pandas.homes.homepage;


import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.PageBean;
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
        homeModel.getHomePageBean(new NetCallbacks<PageBean>() {
            @Override
            public void onSuccess(PageBean pageBean) {
                pageview.setResult(pageBean);
            }

            @Override
            public void onError(String errorMsg) {
                pageview.showMessage(errorMsg);
            }
        });
    }
}
