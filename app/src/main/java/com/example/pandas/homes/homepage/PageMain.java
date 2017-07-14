package com.example.pandas.homes.homepage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.app.App;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.personal.LoginActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 */

public class PageMain extends BaseFragment implements PageContract.View {
    @Bind(R.id.home_logo_img)
    ImageView homeLogoImg;
    @Bind(R.id.home_personal)
    ImageView homePersonal;
    @Bind(R.id.home_interactive)
    ImageView homeInteractive;
    @Bind(R.id.home_xrecyclerview)
    XRecyclerView homeXrecyclerview;
    private PageContract.Presenter presenter;
    private ArrayList<HomePageBean.DataBean> list;
    private HomePageAdapter homePageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_page;
    }

    @Override
    protected void init(View view) {
        list=new ArrayList<>();
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
    public void setResult(final HomePageBean netBean) {
        final HomePageBean.DataBean data = netBean.getData();

        list.add(data);
        homeXrecyclerview.setHasFixedSize(true);
        homeXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        homeXrecyclerview.setLoadingMoreEnabled(false);
        homeXrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        homeXrecyclerview.setArrowImageView(R.mipmap.xlistview_arrow);
        homeXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list.add(data);
                        homeXrecyclerview.refreshComplete();
                        homePageAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onLoadMore() {

            }
        });

        homePageAdapter = new HomePageAdapter(getActivity(),list);
        homeXrecyclerview.setAdapter(homePageAdapter);

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(PageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick({R.id.home_personal, R.id.home_interactive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_personal:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.home_interactive:
                break;
        }
    }
}
