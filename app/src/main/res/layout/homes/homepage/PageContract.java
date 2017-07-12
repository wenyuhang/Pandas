package com.example.apps.homes.homepage;


/**
 * Created by 联想 on 2017/7/12.
 */

public interface PageContract {
    /**
     * view接口
     */
    interface View extends com.example.apps.base.BaseView<Presenter> {
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
        void setResult(com.example.apps.model.datebean.PageBean netBean);

        /**
         * 显示返回信息（成功或失败）
         * @param msg
         */
        void showMessage(String msg);
    }

    /**
     * presenter接口
     */
    interface Presenter extends com.example.apps.base.BasePresenter {

    }
}
