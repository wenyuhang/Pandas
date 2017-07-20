package com.example.pandas.homes.homepandalive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.config.ACache;
import com.example.pandas.homes.homepandalive.main_live.PandaLiveMainFragment;
import com.example.pandas.homes.homepandalive.wonderfu_moment.MarvellousFragment;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.personal.PersonalCenterActivity;
import com.example.pandas.utils.PopupWindowUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 * 熊猫直播页面
 */

public class PandaLiveMain extends BaseFragment implements SendingContract.View{

    @Bind(R.id.pandaLive_login)
    ImageView pandaLiveLogin;
    @Bind(R.id.pandaLive_tablayout)
    TabLayout pandaLiveTablayout;
    @Bind(R.id.pandaLive_viewpager)
    ViewPager pandaLiveViewpager;

    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> strings;
    private SendingContract.Presenter present;
    private ArrayList<LiveTabBean.TablistBean> tablistBeen;
    private PandaLiveTabAdapter adapter;
    private PopupWindowUtils pop;
    private LiveTabBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandalive;
    }

    @Override
    protected void init(View view) {
        showProgressDialog();
        new PandaLivePresent(this);
        tablistBeen = new ArrayList<>();
        fragmentArrayList = new ArrayList<>();
        strings = new ArrayList<>();

        adapter = new PandaLiveTabAdapter(getActivity().getSupportFragmentManager(),fragmentArrayList,strings);
        pandaLiveViewpager.setAdapter(adapter);

        pandaLiveTablayout.setupWithViewPager(pandaLiveViewpager);
        pandaLiveTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        pandaLiveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonalCenterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void loadData() {
        present.strat();
    }


    @OnClick(R.id.pandaLive_login)
    public void onViewClicked() {

    }

    @Override
    public void setPresenter(SendingContract.Presenter presenter) {
        this.present = presenter;
    }

    @Override
    public void showProgressDialog() {
        pop = PopupWindowUtils.getInstance(getActivity());
        pop.startPopup();

    }

    @Override
    public void dismissProgressDialog() {
        pop.stopPopup();
    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {
        this.bean = bean;
        if(bean !=null) {
            dismissProgressDialog();
        }
        ACache aCache = ACache.get(getActivity());
//        for (int pos=0;pos<bean.getTablist().size();pos++){
//            aCache.put(pos,bean.getTablist().get(0).getTitle());
//            aCache.put("",bean.getTablist().get(0).getId());
//        }
//        aCache.put("list",bean.getTablist());
        tablistBeen.addAll(bean.getTablist());

        boolean flg = false;
        boolean fff = false;
        int pos =0;
        for (int i=0;i<tablistBeen.size();i++){
            if(i==0) {
                flg = true;
                pos = 0;
            }else {
                fff = true;
                pos=i;
            }
        }
        if(flg) {
            PandaLiveMainFragment pandaLiveMainFragment = new PandaLiveMainFragment();
            fragmentArrayList.add(0,pandaLiveMainFragment);
            strings.add(0,tablistBeen.get(0).getTitle());
            adapter.notifyDataSetChanged();
        }
        if(fff) {
            for (int o=1;o<=pos;o++){
                MarvellousFragment marvellousFragment = new MarvellousFragment();
                Bundle bundle = new Bundle();
                bundle.putString("vsid",tablistBeen.get(o).getId());
                marvellousFragment.setArguments(bundle);
                fragmentArrayList.add(o,marvellousFragment);
                strings.add(o,tablistBeen.get(o).getTitle());
        }
                adapter.notifyDataSetChanged();
        }

    }


    @Override
    public void setOtherTabBean(OtherTabDetail bean) {

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
}
