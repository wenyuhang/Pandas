package com.example.pandas.homes.homepandalive;


import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

public class PandaLivePresent implements SendingContract.Presenter{
    private IHomeModel iHomeModel;
    private SendingContract.View seeding;
    public PandaLivePresent(SendingContract.View seedingView){
        this.seeding = seedingView;
        seeding.setPresenter(this);
        this.iHomeModel = new IHomeImpl();
    }

    @Override
    public void strat() {
        iHomeModel.getSendingBean(new NetCallbacks<SendingBean>() {
            @Override
            public void onSuccess(SendingBean sendingBean) {
                seeding.setPandaLiveDate(sendingBean);
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
            }
        });
    }
}
