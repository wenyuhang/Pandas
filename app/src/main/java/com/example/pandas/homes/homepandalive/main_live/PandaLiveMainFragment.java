package com.example.pandas.homes.homepandalive.main_live;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.homes.homepandalive.PandaLivePresent;
import com.example.pandas.homes.homepandalive.SendingContract;
import com.example.pandas.homes.homepandalive.main_live.live_fragment.MultiAngleFragment;
import com.example.pandas.homes.homepandalive.main_live.live_fragment.WatchChatFragment;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.utils.LogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 直播页面
 */
public class PandaLiveMainFragment extends BaseFragment implements SendingContract.View {
    @Bind(R.id.pandaLive_pandaFirst)
    ImageView pandaLivePandaFirst;
    @Bind(R.id.pandaLive_detailDown)
    ImageView pandaLiveDetailDown;
    @Bind(R.id.pandaLive_detailUp)
    ImageView pandaLiveDetailUp;
    @Bind(R.id.pandaLive_showIntroduction)
    RelativeLayout pandaLiveShowIntroduction;
    @Bind(R.id.pandaLive_introduction)
    TextView pandaLiveIntroduction;
    @Bind(R.id.pandaLive_introductionDV)
    View pandaLiveIntroductionDV;
    @Bind(R.id.pandaLive_bookMark_tab)
    TabLayout pandaLiveBookMarkTab;
    @Bind(R.id.pandaLive_sendingLinear)
    LinearLayout pandaLiveSendingLinear;
    @Bind(R.id.panda_live_mainTitle)
    TextView pandaLiveMainTitle;
    @Bind(R.id.pandaLive_mainPager)
    ViewPager pandaLiveMainPager;
    @Bind(R.id.panda_live_showLinear)
    LinearLayout pandaLiveShowLinear;
    @Bind(R.id.live_main_stick)
    ScrollView liveMainStick;

    private SendingContract.Presenter present;
    private int singleEven = 1;
    private ArrayList<Fragment> mainLiveFragments;
    private MainLiveAdapter adapter;
    private boolean flg = false;

    @Override
    protected int getLayoutId() {
        return R.layout.live_main_fragment;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresent(this);
        mainLiveFragments = new ArrayList<>();

        liveMainStick.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    protected void loadData() {
        present.strat();

        adapter = new MainLiveAdapter(getActivity().getSupportFragmentManager(), mainLiveFragments);
        pandaLiveMainPager.setAdapter(adapter);

        pandaLiveBookMarkTab.setupWithViewPager(pandaLiveMainPager);
        pandaLiveBookMarkTab.setTabMode(TabLayout.MODE_FIXED);

        pandaLiveBookMarkTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    Intent intent = new Intent();
                    intent.setAction("com.pandas.tag");
                    getActivity().sendBroadcast(intent);
//                    pandaLiveMainPager.setTag(sticky);
                    ViewGroup.LayoutParams layoutParams = pandaLiveMainPager.getLayoutParams();
                    if (flg) {
                        layoutParams.height = 660;
                        //            让上面的view横向   获取焦点 防止点击时跳到下面gridview  里的 第一条
                        pandaLiveShowIntroduction.setFocusable(true);
                        pandaLiveShowIntroduction.setFocusableInTouchMode(true);
                        pandaLiveShowIntroduction.requestFocus();
                    } else {
                        layoutParams.height = 580;
                        //          把上面的tablayout设为焦点 防止 直接显示第一条
                        pandaLiveBookMarkTab.setFocusable(true);
                        pandaLiveBookMarkTab.setFocusableInTouchMode(true);
                        pandaLiveBookMarkTab.requestFocus();
                    }
                } else if (tab.getPosition() == 0) {
                    pandaLiveBookMarkTab.setTag("sticky");
                    ViewGroup.LayoutParams layoutParams = pandaLiveMainPager.getLayoutParams();
                    layoutParams.height = 1050;
                    if(flg) {
                        //            让上面的view横向   获取焦点 防止点击时跳到下面gridview  里的 第一条
                        pandaLiveShowIntroduction.setFocusable(true);
                        pandaLiveShowIntroduction.setFocusableInTouchMode(true);
                        pandaLiveShowIntroduction.requestFocus();

                    }else {
                        //          把上面的tablayout设为焦点 防止 直接显示第一条
                        pandaLiveBookMarkTab.setFocusable(true);
                        pandaLiveBookMarkTab.setFocusableInTouchMode(true);
                        pandaLiveBookMarkTab.requestFocus();

                    }
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
        Glide.with(getActivity()).load(bean.getLive().get(0).getImage()).into(pandaLivePandaFirst);
        pandaLiveMainTitle.setText(bean.getLive().get(0).getTitle());
        pandaLiveIntroduction.setText(bean.getLive().get(0).getBrief());

        MultiAngleFragment multiAngleFragment = new MultiAngleFragment();

        WatchChatFragment watchChatFragment = new WatchChatFragment();
        Bundle bundle1 = new Bundle();
        String url1 = bean.getBookmark().getWatchTalk().get(0).getUrl();
        bundle1.putString("wathch_url", url1);
        LogUtils.setLog("plmf", url1);
        Log.e("PandaLiveMainFragment", bean.getBookmark().getWatchTalk().get(0).getUrl());
        multiAngleFragment.setArguments(bundle1);


        mainLiveFragments.add(multiAngleFragment);
        mainLiveFragments.add(watchChatFragment);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {

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

    @OnClick(R.id.pandaLive_showIntroduction)
    public void onViewClicked() {
        if (singleEven % 2 != 0) {
//            展开详情
            pandaLiveDetailDown.setVisibility(View.GONE);
            pandaLiveDetailUp.setVisibility(View.VISIBLE);
            pandaLiveShowLinear.setVisibility(View.VISIBLE);
            singleEven++;
            flg = true;


        } else {
//            关闭详情
            pandaLiveDetailDown.setVisibility(View.VISIBLE);
            pandaLiveDetailUp.setVisibility(View.GONE);
            pandaLiveShowLinear.setVisibility(View.GONE);
            singleEven++;
            flg = false;

//          把上面的tablayout设为焦点 防止 直接显示第一条
            pandaLiveBookMarkTab.setFocusable(true);
            pandaLiveBookMarkTab.setFocusableInTouchMode(true);
            pandaLiveBookMarkTab.requestFocus();
        }
    }
}