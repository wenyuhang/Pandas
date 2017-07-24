package com.example.pandas.homes.pandaculture.contract;


import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;

/**
 * Created by INS7566 on 2017/7/13.
 */

public interface CultureContract {

    interface View extends BaseView<CultureContract.Presenter> {
        /**
         * 显示进度dialog
         */
        void showProgressDialog();
        /**
         * 关闭进度dialog
         */
        void dismissDialog();

        /**
         * 返回网络数据
         * @param pandaCultureEntity
         */
//  请求首个页面的数据
        void setResult(PandaCultureEntity pandaCultureEntity);


//       视频全屏播放的请求url
        void setStartVideoURL(VideoStartBean videoStartBean);
        /**
         * 显示返回信息（成功或失败）
         * @param msg
         */
        void showMessage(String msg);

    }
    interface Presenter extends BasePresenter {

       void startVideo(String pid);

    }

}
