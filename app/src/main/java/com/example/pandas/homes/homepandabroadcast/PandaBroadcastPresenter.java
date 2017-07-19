package com.example.pandas.homes.homepandabroadcast;

import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PandaBroadcastPresenter implements PandaBroadcastContract.Presenter {

    private IHomeModel homeModel;
    private PandaBroadcastContract.View pandview;
    public PandaBroadcastPresenter(PandaBroadcastContract.View pandview){
        this.pandview = pandview;
        this.pandview.setPresenter(this);
        homeModel = new IHomeImpl();
    }


    @Override
    public void strat() {
        homeModel.getTitleBean(new NetCallbacks<TitleBean>() {
            @Override
            public void onSuccess(TitleBean titleBean) {
                pandview.setResult1(titleBean);
                IHomeImpl.ihttp.get(titleBean.getData().getListurl(), null, new NetCallbacks<PdBBean>() {
                    @Override
                    public void onSuccess(PdBBean pdBBean) {
                        pandview.setResult(pdBBean);

                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });
            }

            @Override
            public void onError(String errorMsg) {
                pandview.showMessage(errorMsg);
            }
        });
    }
}
