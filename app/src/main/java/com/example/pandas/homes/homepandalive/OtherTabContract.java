package com.example.pandas.homes.homepandalive;


import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.PlayBean;

public interface OtherTabContract {
    interface Presenter extends BasePresenter{
        void loadMore(String vsid,String n,String serviceId,String o,String of,String p);
        void getPlay(String pid);
    }

    interface View extends BaseView<Presenter>{
        /**
         * 显示进度条
         */
        void showProgressDialog();

        /**
         * 隐藏进度条
         */
        void dismissProgressDialog();

        /**
         * 返回 tablayout 剩下的所有数据
         * @param bean
         */
        void setOtherTabBean(OtherTabDetail bean);

        /**
         * 返回要播放视频的路径
         */
        void setPlayBean(PlayBean bean);

        /**
         * 显示 是否成功
         * @param message
         */
        void setShowMessage(String message);
    }
}
