package com.example.pandas.homes.homelivechina;

import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/13.
 */

public class LiveChinaPresenter implements LiveChinaContract.Presenter{
    private LiveChinaContract.View liveview;
    private IHomeImpl iHome;

    public LiveChinaPresenter(LiveChinaContract.View liveview) {
        this.liveview = liveview;
        this.liveview.setPresenter(this);
        iHome=new IHomeImpl();
    }

    @Override
    public void onRefrsh() {

    }

    @Override
    public void onload(String str) {
        iHome.getLiveChinaBean(str, new NetCallbacks<LiveChinaBean>() {
            @Override
            public void onSuccess(LiveChinaBean liveChinaBean) {
                liveview.setResult(liveChinaBean);
            }

            @Override
            public void onError(String errorMsg) {
                liveview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void strat() {
        iHome.getSeneryBean(new NetCallbacks<SceneryBean>() {
            @Override
            public void onSuccess(SceneryBean sceneryBean) {
                liveview.setResult2(sceneryBean);
            }

            @Override
            public void onError(String errorMsg) {
                liveview.showMessage(errorMsg);
            }
        });
    }
}
