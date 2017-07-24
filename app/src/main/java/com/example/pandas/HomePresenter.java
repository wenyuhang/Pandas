package com.example.pandas;

import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.UpDateBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/24.
 */

public class HomePresenter implements HomeContract.Presenter{
    private IHomeModel homeModel;
    private HomeContract.View homeview;
    public HomePresenter(HomeContract.View homeview) {
        this.homeview=homeview;
        homeModel=new IHomeImpl();
        this.homeview.setPresenter(this);
    }

    @Override
    public void strat() {
        homeModel.getUpDateBean(new NetCallbacks<UpDateBean>() {
            @Override
            public void onSuccess(UpDateBean upDateBean) {
                homeview.setResult(upDateBean);
            }

            @Override
            public void onError(String errorMsg) {
                homeview.showMessage(errorMsg);
            }
        });
    }
}
