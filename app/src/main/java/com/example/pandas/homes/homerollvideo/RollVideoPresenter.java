package com.example.pandas.homes.homerollvideo;

import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.RollvideoBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by yan on 2017/7/13.
 */

public class RollVideoPresenter implements RollVideoContract.Presenter{
    private RollVideoContract.View rollview;
    private IHomeModel homeModel;
    public RollVideoPresenter(RollVideoContract.View rollview){
        this.rollview=rollview;
        this.rollview.setPresenter(this);
        homeModel=new IHomeImpl();
    }
    @Override
    public void strat() {
     homeModel.getRollVideoBean(new NetCallbacks<RollvideoBean>() {
         @Override
         public void onSuccess(RollvideoBean rollvideoBean) {
             rollview.setResult(rollvideoBean);
         }

         @Override
         public void onError(String errorMsg) {
           rollview.showMessage(errorMsg);
         }
     });
    }
}
