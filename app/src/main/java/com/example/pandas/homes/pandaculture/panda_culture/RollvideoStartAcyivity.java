package com.example.pandas.homes.pandaculture.panda_culture;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.homes.pandaculture.contract.CultureContract;

public class RollvideoStartAcyivity extends BaseActivity implements CultureContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.activity_rollvideo_start_acyivity;
    }

    @Override
    public void initview() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(PandaCultureEntity pandaCultureEntity) {

    }

    @Override
    public void setVideoResult(CCTVBaen cctvBaen) {

    }

    @Override
    public void setvideoURl(PlayVideo playVideo) {

    }

    @Override
    public void setStartVideoURL(VideoStartBean videoStartBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(CultureContract.Presenter presenter) {

    }
}
