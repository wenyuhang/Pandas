package com.example.pandas.personal.homeinteractive;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;

/**
 * Created by Nicky on 2017/7/15.
 */

public interface InteractiveContract {

    interface View extends BaseView<Presenter>{
        void showProgressDialog();
        void dismissDialog();
        void setResult(InteractiveInfoBean interactiveInfoBean);
        void showMessage(String msg);
    }

    interface Presenter extends BasePresenter{

    }
}
