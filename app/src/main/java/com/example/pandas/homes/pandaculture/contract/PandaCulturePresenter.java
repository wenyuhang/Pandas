package com.example.pandas.homes.pandaculture.contract;

import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.model.biz.IHomeImpl;
import com.example.pandas.model.biz.IHomeModel;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaCulturePresenter implements CultureContract.Presenter {

    private CultureContract.View cultureview;
    private IHomeModel iHomeModel;

    public PandaCulturePresenter(CultureContract.View view) {

        this.cultureview = view;
        cultureview.setPresenter(this);
        this.iHomeModel = new IHomeImpl();

    }


    @Override
    public void strat() {
        iHomeModel.getRollVideoBean(new NetCallbacks<PandaCultureEntity>() {
            @Override
            public void onSuccess(PandaCultureEntity pandaCultureEntity) {
                cultureview.setResult(pandaCultureEntity);
            }

            @Override
            public void onError(String errorMsg) {
               cultureview.showMessage(errorMsg);
            }
        });
       
    }


    @Override
    public void loadDate(String n, String vsid, String p, String serviceId,String em) {
        iHomeModel.getCCTVVideo(n,vsid,p,serviceId,em, new NetCallbacks<CCTVBaen>() {
            @Override
            public void onSuccess(CCTVBaen cctvBaen) {
               cultureview.setVideoResult(cctvBaen);
            }

            @Override
            public void onError(String errorMsg) {
                cultureview.showMessage(errorMsg);

            }
        });
    }
    public void playVideo(String pid){
        iHomeModel.getVideoUrl(pid, new NetCallbacks<PlayVideo>() {
            @Override
            public void onSuccess(PlayVideo playVideo) {
              cultureview.setvideoURl(playVideo);
            }

            @Override
            public void onError(String errorMsg) {

             cultureview.showMessage(errorMsg);
            }
        });

    }
    public void startVideo(String pid){
        iHomeModel.getStartVideo(pid, new NetCallbacks<VideoStartBean>() {
            @Override
            public void onSuccess(VideoStartBean videoStartBean) {
                cultureview.setStartVideoURL(videoStartBean);
            }

            @Override
            public void onError(String errorMsg) {
              cultureview.showMessage(errorMsg);
            }
        });
    }




}