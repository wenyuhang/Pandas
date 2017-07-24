package com.example.pandas.homes.homelivechina;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;


/**
 * Created by 联想 on 2017/7/13.
 */

public interface LiveChinaContract {
    /**
     * view接口
     */
    interface View extends BaseView<Presenter> {

        void setResult(LiveChinaBean netBean);

        void setResult2(SceneryBean netBean);

        void showMessage(String msg);
    }

    /**
     * presenter接口
     */
    interface Presenter extends BasePresenter {
        void onRefrsh();
        void onload(String str);
    }
}
