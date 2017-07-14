package com.example.pandas.homes.homerollvideo;

import com.example.pandas.base.BasePresenter;
import com.example.pandas.base.BaseView;
import com.example.pandas.model.datebean.RollvideoBean;

/**
 * Created by yan on 2017/7/13.
 */

public class RollVideoContract {

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
         * @param rollvideoBean
         */
        void setResult(RollvideoBean rollvideoBean);

        /**
         * 显示返回信息（成功或失败）
         * @param msg
         */
        void showMessage(String msg);
    }
    interface Presenter extends BasePresenter{

    }
}
