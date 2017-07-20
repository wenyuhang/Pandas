package com.example.pandas.homes.homepandabroadcast;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;

/**
 * Created by Administrator on 2017/7/13.
 */

public interface PandaBroadcastContract {
//
    interface Presenter extends BasePresenter {

    }
    interface View extends BaseView<Presenter>  {
        void showProgressDialog();
        void dismissDialog();
        void setResult(PdBBean pdBBean);
        void setResult1(TitleBean titleBean);
        void showMessage(String msg);

    }

}
