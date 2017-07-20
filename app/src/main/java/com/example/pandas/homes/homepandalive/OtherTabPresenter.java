package com.example.pandas.homes.homepandalive;


import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.PlayBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

public class OtherTabPresenter implements OtherTabContract.Presenter {
    IHomeModel iHomeModel;
    OtherTabContract.View otherTab;
    public OtherTabPresenter(OtherTabContract.View otherTab){
        iHomeModel = new IHomeImpl();
        otherTab.setPresenter(this);
        this.otherTab = otherTab;
    }
    @Override
    public void strat() {
          otherTab.showProgressDialog();
    }

    @Override
    public void loadMore(String vsid, String n, String serviceId, String o, String of, String p) {
        iHomeModel.getOtherDetailBean(vsid,n,serviceId,o,of,p, new NetCallbacks<OtherTabDetail>() {
            @Override
            public void onSuccess(OtherTabDetail otherTabDetail) {
                otherTab.setOtherTabBean(otherTabDetail);
                otherTab.dismissProgressDialog();
            }

            @Override
            public void onError(String errorMsg) {
                otherTab.dismissProgressDialog();
                otherTab.setShowMessage(errorMsg);
            }
        });
    }

    @Override
    public void getPlay(String pid) {
        iHomeModel.getPandaPlayBean(pid, new NetCallbacks<PlayBean>() {
            @Override
            public void onSuccess(PlayBean playBean) {
                otherTab.setPlayBean(playBean);
            }

            @Override
            public void onError(String errorMsg) {
                otherTab.setShowMessage(errorMsg);
            }
        });
    }
}
