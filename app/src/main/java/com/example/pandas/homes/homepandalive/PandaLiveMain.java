package com.example.pandas.homes.homepandalive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homepandalive.main_live.PandaLiveMainFragment;
import com.example.pandas.homes.homepandalive.wonderfu_moment.MarvellousFragment;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


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
    private SendingContract.Presenter present;
    private ArrayList<LiveTabBean.TablistBean> tablistBeen;
    private EventBus eventBus;
    private MarvellousFragment marvellousFragment;
    private MarvellousFragment otherTabDetail1;
    private MarvellousFragment otherTabDetail2;
    private MarvellousFragment otherTabDetail3;
    private MarvellousFragment otherTabDetail4;
    private MarvellousFragment otherTabDetail5;
    private MarvellousFragment otherTabDetail6;
    private MarvellousFragment otherTabDetail7;
    private PandaLiveTabAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_pandalive;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresent(this);

        tablistBeen = new ArrayList<>();
        fragmentArrayList = new ArrayList<>();

        PandaLiveMainFragment pandaLiveMainFragment = new PandaLiveMainFragment();

        fragmentArrayList.add(pandaLiveMainFragment);

        adapter = new PandaLiveTabAdapter(getActivity().getSupportFragmentManager(),fragmentArrayList);
        pandaLiveViewpager.setAdapter(adapter);

        pandaLiveTablayout.setupWithViewPager(pandaLiveViewpager);
        pandaLiveTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

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

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void setPandaLiveDate(SendingBean bean) {

    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {
        tablistBeen.addAll(bean.getTablist());

        marvellousFragment = new MarvellousFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("vsid",tablistBeen.get(1).getId());
        marvellousFragment.setArguments(bundle1);
        Log.e("PandaLiveMain", "argument1");

        otherTabDetail1 = new MarvellousFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("vsid",tablistBeen.get(2).getId());
        otherTabDetail1.setArguments(bundle2);
        Log.e("PandaLiveMain", "argument2");

        otherTabDetail2 = new MarvellousFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("vsid",tablistBeen.get(3).getId());
        otherTabDetail2.setArguments(bundle3);


        otherTabDetail3 = new MarvellousFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("vsid",tablistBeen.get(4).getId());
        otherTabDetail3.setArguments(bundle4);


        otherTabDetail4 = new MarvellousFragment();
        Bundle bundle5 = new Bundle();
        bundle5.putString("vsid",tablistBeen.get(5).getId());
        otherTabDetail4.setArguments(bundle5);


        otherTabDetail5 = new MarvellousFragment();
        Bundle bundle6 = new Bundle();
        bundle6.putString("vsid",tablistBeen.get(6).getId());
        otherTabDetail5.setArguments(bundle6);


        otherTabDetail6 = new MarvellousFragment();
        Bundle bundle7 = new Bundle();
        bundle7.putString("vsid",tablistBeen.get(7).getId());
        otherTabDetail6.setArguments(bundle7);


        otherTabDetail7 = new MarvellousFragment();
        Bundle bundle8 = new Bundle();
        bundle8.putString("vsid",tablistBeen.get(8).getId());
        otherTabDetail7.setArguments(bundle8);


        fragmentArrayList.add(marvellousFragment);
        fragmentArrayList.add(otherTabDetail1);
        fragmentArrayList.add(otherTabDetail2);
        fragmentArrayList.add(otherTabDetail3);
        fragmentArrayList.add(otherTabDetail4);
        fragmentArrayList.add(otherTabDetail5);
        fragmentArrayList.add(otherTabDetail6);
        fragmentArrayList.add(otherTabDetail7);

        adapter.notifyDataSetChanged();

        pandaLiveTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        break;
                    case 1:
                        EventBus.getDefault().postSticky(tablistBeen.get(1).getId());
//                        Bundle bundle1 = new Bundle();
//                        bundle1.putString("vsid",tablistBeen.get(1).getId());
//                        marvellousFragment.setArguments(bundle1);
                        break;
                    case 2:
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("vsid",tablistBeen.get(3).getId());
                        otherTabDetail2.setArguments(bundle3);
                        EventBus.getDefault().postSticky(tablistBeen.get(2).getId());
                        break;
                    case 3:

                        EventBus.getDefault().postSticky(tablistBeen.get(3).getId());
                        break;
                    case 4:

                        EventBus.getDefault().postSticky(tablistBeen.get(4).getId());
                        break;
                    case 5:

                        EventBus.getDefault().postSticky(tablistBeen.get(5).getId());
                        break;
                    case 6:

                        EventBus.getDefault().postSticky(tablistBeen.get(6).getId());
                        break;
                    case 7:

                        EventBus.getDefault().postSticky(tablistBeen.get(7).getId());
                        break;
                    case 8:

                        EventBus.getDefault().postSticky(tablistBeen.get(8).getId());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
