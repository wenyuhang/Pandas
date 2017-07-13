package com.example.pandas.homes.homelivechina;

import com.example.pandas.model.biz.IHomeImpl;

/**
 * Created by 联想 on 2017/7/13.
 */

public class LiveChinaPresenter implements LiveChinaContract.Presenter{
    private LiveChinaContract.View liveview;
    private IHomeImpl iHome;

    public LiveChinaPresenter(LiveChinaContract.View liveview) {
        this.liveview = liveview;

        iHome=new IHomeImpl();
    }

    @Override
    public void onRefrsh() {

    }

    @Override
    public void strat() {

    }
}
