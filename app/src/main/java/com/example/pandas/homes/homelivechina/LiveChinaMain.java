package com.example.pandas.homes.homelivechina;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homepage.PageContract;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.personal.PersonalCenterActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;



/**
 * Created by 联想 on 2017/7/10.
 */

public class LiveChinaMain extends BaseFragment implements LiveChinaContract.View {


    @Bind(R.id.LiveChina_login)
    ImageView LiveChinaLogin;
    @Bind(R.id.liveChina_tablayout)
    TabLayout liveChinaTablayout;
    @Bind(R.id.sure)
    Button sure;
    @Bind(R.id.liveChina_viewpager)
    ViewPager liveChinaViewpager;
    private LCPpageAdapter lcPpageAdapter;
    private ArrayList<BaseFragment> list;
    private ArrayList<String> strings;

    @Override
    protected int getLayoutId() {
        return R.layout.main_livechina;
    }

    @Override
    protected void init(View view) {
        list = new ArrayList<BaseFragment>();

        strings = new ArrayList<>();
        for(int i = 0; i <4 ; i++) {
            PageFragment pageFragment = new PageFragment();
            list.add(pageFragment);
            strings.add("黄山"+i);
        }
        lcPpageAdapter = new LCPpageAdapter(getActivity().getSupportFragmentManager(), list, strings);
        liveChinaViewpager.setAdapter(lcPpageAdapter);
        liveChinaTablayout.setupWithViewPager(liveChinaViewpager);
        liveChinaTablayout.setTabMode(TabLayout.MODE_FIXED);
        liveChinaTablayout.setMinimumWidth(200);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(HomePageBean netBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(PageContract.Presenter presenter) {

    }


    @OnClick({R.id.LiveChina_login, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LiveChina_login:
                startActivity(new Intent(getActivity(), PersonalCenterActivity.class));
                break;
            case R.id.sure:

                PageFragment pageFragment = new PageFragment();
                list.add(pageFragment);
                strings.add("黄山"+520);
                lcPpageAdapter.notifyDataSetChanged();
                break;
        }
    }
}
