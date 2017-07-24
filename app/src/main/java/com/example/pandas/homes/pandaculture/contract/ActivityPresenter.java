package com.example.pandas.homes.pandaculture.contract;

import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by yan on 2017/7/21.
 */

public class ActivityPresenter implements ActivityContract.Presenter {
    private ActivityContract.View activitview;
    private IHomeModel iHome;
    public ActivityPresenter(ActivityContract.View view){
        this.activitview=view;
        activitview.setPresenter(this);
        this.iHome=new IHomeImpl();


    }
    @Override
    public void loadDate(String n, String vsid, String p, String serviceId, String em) {
        iHome.getCCTVVideo(n,vsid,p,serviceId,em, new NetCallbacks<CCTVBaen>() {
            @Override
            public void onSuccess(CCTVBaen cctvBaen) {
                activitview.setVideoResult(cctvBaen);
            }

            @Override
            public void onError(String errorMsg) {
                activitview.showMessage(errorMsg);

            }
        });
    }

    @Override
    public void playVideo(String pid) {
        iHome.getVideoUrl(pid, new NetCallbacks<PlayVideo>() {
            @Override
            public void onSuccess(PlayVideo playVideo) {
                activitview.setvideoURl(playVideo);
            }

            @Override
            public void onError(String errorMsg) {

                activitview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void strat() {

    }

}
