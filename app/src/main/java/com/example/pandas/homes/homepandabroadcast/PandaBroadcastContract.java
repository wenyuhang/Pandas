package com.example.pandas.homes.homepandabroadcast;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.PdBBean;

/**
 * Created by Administrator on 2017/7/13.
 */

public interface PandaBroadcastContract {
//
    interface Presenter extends BasePresenter {
        void lodeMore(String path, String primary_id, String serviceId);
    }
    interface View extends BaseView<Presenter>  {
        void showProgressDialog();
        void dismissDialog();
        void setResult(PdBBean pdBBean);
        void showMessage(String msg);

    }

}
