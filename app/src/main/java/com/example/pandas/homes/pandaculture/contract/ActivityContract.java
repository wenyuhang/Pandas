package com.example.pandas.homes.pandaculture.contract;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;

/**
 * Created by yan on 2017/7/21.
 */

public interface ActivityContract {
    interface View extends BaseView<ActivityContract.Presenter> {
        //  请求详情的数据
        void setVideoResult(CCTVBaen cctvBaen);
        //  请求视频的方法
        void setvideoURl(PlayVideo playVideo);
        /**
         * 显示返回信息（成功或失败）
         * @param msg
         */
        void showMessage(String msg);

    }
    interface Presenter extends BasePresenter {
        void loadDate(String n, String vsid, String p,String serviceId,String em);
        void playVideo(String pid);
    }


}
