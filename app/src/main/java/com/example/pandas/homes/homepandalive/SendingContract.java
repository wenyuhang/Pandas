package com.example.pandas.homes.homepandalive;


import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;

public interface SendingContract {
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
         * 返回直播首页数据
         * @param bean
         */
        void setPandaLiveDate(SendingBean bean);

        /**
         * 返回tablayout  列表数据
         * @param bean
         */
        void setLiveTabBean(LiveTabBean bean);

        /**
         * 返回 tablayout 剩下的所有数据
         * @param bean
         */
        void setOtherTabBean(OtherTabDetail bean);

        /**
         * 显示 是否成功
         * @param message
         */
        void setShowMessage(String message);

        /**
         * 返回多视角 数据
         * @param bean
         */
        void setMultipleBean(MultipleBean bean);

        /**
         * 返回 边看边聊数据
         * @param bean
         */
        void setWatchAtBean(WatchChatBean bean);
    };


    interface Presenter extends BasePresenter{
        void loadMore(String vsid,String n,String serviceId,String o,String of,String p);
        void watchAt(String app, String itemId, String nature, String page);
    }
}
