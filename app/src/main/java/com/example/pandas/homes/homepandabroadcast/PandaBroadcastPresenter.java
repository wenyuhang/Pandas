package com.example.pandas.homes.homepandabroadcast;

import android.util.Log;

import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.PdBBean;
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
    public void lodeMore(String path, String primary_id, String serviceId) {
        Log.d("PandaBroadcastPresenter",path);
        Log.d("PandaBroadcastPresenter",primary_id);
        Log.d("PandaBroadcastPresenter",serviceId);
        homeModel.getPdBBean(path, primary_id, serviceId, new NetCallbacks<PdBBean>() {
            @Override
            public void onSuccess(PdBBean pdBBean) {
                pandview.setResult(pdBBean);
            }

            @Override
            public void onError(String errorMsg) {
                pandview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void strat() {

    }
}
