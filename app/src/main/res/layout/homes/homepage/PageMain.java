package com.example.apps.homes.homepage;

import android.util.Log;
import android.view.View;

import com.example.apps.R;
import com.example.apps.base.BaseFragment;

/**
 * Created by 联想 on 2017/7/10.
 */

public class PageMain extends BaseFragment implements PageContract.View{
    private PageContract.Presenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.main_page;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

        presenter.strat();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(com.example.apps.model.datebean.PageBean netBean) {
        Log.e("PageMain", netBean.getMessage());
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(PageContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
