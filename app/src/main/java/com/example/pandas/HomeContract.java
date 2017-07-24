package com.example.pandas;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.UpDateBean;

/**
 * Created by 联想 on 2017/7/24.
 */

public class HomeContract {

    interface View extends BaseView<Presenter> {


        void setResult(UpDateBean netBean);


        void showMessage(String msg);
    }


    interface Presenter extends BasePresenter {
    }
}
