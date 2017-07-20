package com.example.pandas.homes.homepandalive;


import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
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
                seeding.dismissProgressDialog();
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
                seeding.dismissProgressDialog();
            }
        });

        iHomeModel.getLiveTabBean(new NetCallbacks<LiveTabBean>() {
            @Override
            public void onSuccess(LiveTabBean liveTabBean) {
                seeding.setLiveTabBean(liveTabBean);
                seeding.dismissProgressDialog();
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
                seeding.dismissProgressDialog();
            }
        });

        iHomeModel.getMultipleBean(new NetCallbacks<MultipleBean>() {
            @Override
            public void onSuccess(MultipleBean multipleBean) {
                seeding.setMultipleBean(multipleBean);
                seeding.dismissProgressDialog();
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
                seeding.dismissProgressDialog();
            }
        });
    }
}
