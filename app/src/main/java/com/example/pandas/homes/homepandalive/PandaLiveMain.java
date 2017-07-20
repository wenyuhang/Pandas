package com.example.pandas.homes.homepandalive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACache;
import com.example.pandas.homes.homepandalive.main_live.PandaLiveMainFragment;
import com.example.pandas.homes.homepandalive.wonderfu_moment.MarvellousFragment;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by 联想 on 2017/7/10.
 * 熊猫直播页面
 */

public class PandaLiveMain extends BaseFragment implements SendingContract.View {

    @Bind(R.id.pandaLive_tablayout)
    TabLayout pandaLiveTablayout;
    @Bind(R.id.pandaLive_viewpager)
    ViewPager pandaLiveViewpager;
    @Bind(R.id.linear_show)
    LinearLayout linearShow;
    @Bind(R.id.pandalive_probar)
    ProgressBar pandaliveProbar;
    @Bind(R.id.pandalive_relalayout)
    RelativeLayout pandaliveRelalayout;

    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> strings;
    private SendingContract.Presenter present;
    private ArrayList<LiveTabBean.TablistBean> tablistBeen;
    private PandaLiveTabAdapter adapter;
    private LiveTabBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandalive;
    }

    @Override
    protected void init(View view) {
        pandaliveRelalayout.setVisibility(View.VISIBLE);
        new PandaLivePresent(this);
        tablistBeen = new ArrayList<>();
        fragmentArrayList = new ArrayList<>();
        strings = new ArrayList<>();

        adapter = new PandaLiveTabAdapter(getActivity().getSupportFragmentManager(), fragmentArrayList, strings);
        pandaLiveViewpager.setAdapter(adapter);

        pandaLiveTablayout.setupWithViewPager(pandaLiveViewpager);
        pandaLiveTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    protected void loadData() {
        present.strat();
    }

    @Override
    public void setPresenter(SendingContract.Presenter presenter) {
        this.present = presenter;
    }

    @Override
    public void showProgressDialog() {
    }

    @Override
    public void dismissProgressDialog() {
        pandaliveRelalayout.setVisibility(View.GONE);
    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {
        if (bean != null) {
            dismissProgressDialog();
        }
        ACache aCache = ACache.get(getActivity());

        tablistBeen.addAll(bean.getTablist());

        for (int i = 0; i < tablistBeen.size(); i++) {
            if (i == 0) {
                PandaLiveMainFragment pandaLiveMainFragment = new PandaLiveMainFragment();
                new PandaLivePresent(pandaLiveMainFragment);
                fragmentArrayList.add(0, pandaLiveMainFragment);
                strings.add(0, tablistBeen.get(0).getTitle());
            } else {
                MarvellousFragment marvellousFragment = new MarvellousFragment();
                Bundle bundle = new Bundle();
                new OtherTabPresenter(marvellousFragment);
                bundle.putString("vsid", tablistBeen.get(i).getId());
                marvellousFragment.setArguments(bundle);
                fragmentArrayList.add(marvellousFragment);
                strings.add(tablistBeen.get(i).getTitle());
            }
        }
        adapter.notifyDataSetChanged();
        pandaLiveViewpager.setOffscreenPageLimit(tablistBeen.size());

    }

    @Override
    public void setShowMessage(String message) {

    }

    @Override
    public void setMultipleBean(MultipleBean bean) {

    }

    @Override
    public void setWatchAtBean(WatchChatBean bean) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
