package com.example.pandas.personal.homeinteractive;

import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by Nicky on 2017/7/15.
 */

public class InteractivePresenter implements InteractiveContract.Presenter {

    private InteractiveContract.View view;
    private IHomeModel homeModel;
    public InteractivePresenter(InteractiveContract.View view){
        this.view=view;
        this.view.setPresenter(this);
        homeModel=new IHomeImpl();
    }


    @Override
    public void strat() {
        homeModel.getInteractiveBeans(new NetCallbacks<InteractiveInfoBean>() {
            @Override
            public void onSuccess(InteractiveInfoBean interactiveInfoBean) {
                view.setResult(interactiveInfoBean);
            }

            @Override
            public void onError(String errorMsg) {
                view.showMessage(errorMsg);
            }
        });
    }
}
