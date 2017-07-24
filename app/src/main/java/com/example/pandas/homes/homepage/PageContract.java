package com.example.pandas.homes.homepage;


import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;

/**
 * Created by 联想 on 2017/7/12.
 */
public interface PageContract {
    /**
     * view接口
     */
    interface View extends BaseView<Presenter> {
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
         * @param netBean
         */
        void setResult(HomePageBean netBean);

        /**
         * 显示返回信息（成功或失败）
         * @param msg
         */
        void showMessage(String msg);

        void setPandaEyesResult(PandaEyeListBean pandaEyeListBean);

        void setCCTVResult(CCTVInfoBean cctvInfoBean);

        void setLightChinaResult(LightChinaBean lightChinaBean);

        void setRotationResult(VideoInfoBean videoInfoBean);

        void setWonderfulResult(VideoInfoBean videoInfoBean);

        void setItemResult(VideoInfoBean videoInfoBean);

        void setPandaWatchResult(VideoInfoBean videoInfoBean);

        void setLightChinaVideoInfo(VideoInfoBean videoInfoBean);
    }

    /**
     * presenter接口
     */
    interface Presenter extends BasePresenter {
        void pandaEyeResult(String url);
        void cctvResult(String url);
        void lightChinaResult(String url);
        void rotationResult(String id);
        void wonderfulResult(String id);
        void itemResult(String id);
        void pandaWatchResult(String id);
        void lightChinaVideoInfo(String id);
    }
}
