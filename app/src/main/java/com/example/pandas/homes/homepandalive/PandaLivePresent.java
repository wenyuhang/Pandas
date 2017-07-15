package com.example.pandas.homes.homepandalive;


import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
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

        iHomeModel.getLiveTabBean(new NetCallbacks<LiveTabBean>() {
            @Override
            public void onSuccess(LiveTabBean liveTabBean) {
                seeding.setLiveTabBean(liveTabBean);
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
            }
        });

        iHomeModel.getMultipleBean(new NetCallbacks<MultipleBean>() {
            @Override
            public void onSuccess(MultipleBean multipleBean) {
                seeding.setMultipleBean(multipleBean);
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
            }
        });
    }


    @Override
    public void loadMore(String vsid, String n, String serviceId, String o, String of, String p) {
        iHomeModel.getOtherDetailBean(vsid,n,serviceId,o,of,p, new NetCallbacks<OtherTabDetail>() {
            @Override
            public void onSuccess(OtherTabDetail otherTabDetail) {
                seeding.setOtherTabBean(otherTabDetail);
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
            }
        });
    }

    @Override
    public void watchAt(String app, String itemId, String nature, String page) {
        iHomeModel.getWatchChatBean(app, itemId, nature, page, new NetCallbacks<WatchChatBean>() {
            @Override
            public void onSuccess(WatchChatBean watchChatBean) {
                seeding.setWatchAtBean(watchChatBean);
            }

            @Override
            public void onError(String errorMsg) {
                seeding.setShowMessage(errorMsg);
            }
        });
    }
}
