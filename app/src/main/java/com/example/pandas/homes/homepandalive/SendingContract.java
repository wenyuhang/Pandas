package com.example.pandas.homes.homepandalive;


import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.pandasending.SendingBean;

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
         * 返回数据
         * @param bean
         */
        void setPandaLiveDate(SendingBean bean);

        /**
         * 显示 是否成功
         * @param message
         */
        void setShowMessage(String message);
    };
    interface Presenter extends BasePresenter{

    }
}
