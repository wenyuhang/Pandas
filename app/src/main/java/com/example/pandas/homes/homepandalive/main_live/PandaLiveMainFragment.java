package com.example.pandas.homes.homepandalive.main_live;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.utils.LogUtils;
import com.example.pandas.utils.NonSwipeableViewPager;

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
    @Bind(R.id.panda_live_mainTitle)
    TextView pandaLiveMainTitle;
    @Bind(R.id.pandaLive_mainPager)
    NonSwipeableViewPager pandaLiveMainPager;
    @Bind(R.id.panda_live_showLinear)
    LinearLayout pandaLiveShowLinear;
    @Bind(R.id.live_main_stick)
    ScrollView liveMainStick;

    private SendingContract.Presenter present;
    private int singleEven = 1;
    private ArrayList<Fragment> mainLiveFragments;
    private MainLiveAdapter adapter;
    private boolean flg = false;
    private boolean sureFirst = false;
    private ArrayList<String> list;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String image_url = intent.getStringExtra("image_url");
            String url = intent.getStringExtra("url");

        }
    };
    private AlertDialog.Builder builder;

    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1000) {
                builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.dialog_play)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;
                builder.create();
                builder.show();
            }else if(msg.what == 2000) {
                LogUtils.setLog("PLMF",list.size()+"::"+mainLiveFragments.size());
                adapter.notifyDataSetChanged();
            }

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.live_main_fragment;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresent(this);
        mainLiveFragments = new ArrayList<>();
        list = new ArrayList<>();

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.sending_url");
        getActivity().registerReceiver(receiver,filter);
    }

    @Override
    protected void loadData() {
        present.strat();

        adapter = new MainLiveAdapter(getActivity().getSupportFragmentManager(), mainLiveFragments,list);
        pandaLiveMainPager.setAdapter(adapter);

        pandaLiveBookMarkTab.setupWithViewPager(pandaLiveMainPager);
        pandaLiveBookMarkTab.setTabMode(TabLayout.MODE_FIXED);

        pandaLiveMainPager.setOffscreenPageLimit(2);

        pandaLiveBookMarkTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    Intent intent = new Intent();
                    intent.setAction("com.pandas.tag");
                    getActivity().sendBroadcast(intent);
                    ViewGroup.LayoutParams layoutParams = pandaLiveMainPager.getLayoutParams();
                    if (flg) {
                        layoutParams.height = 700;
                        //            让上面的view横向   获取焦点 防止点击时跳到下面gridview  里的 第一条
                        pandaLiveShowIntroduction.setFocusable(true);
                        pandaLiveShowIntroduction.setFocusableInTouchMode(true);
                        pandaLiveShowIntroduction.requestFocus();
                    } else {
                        layoutParams.height = 600;
                        //          把上面的tablayout设为焦点 防止 直接显示第一条
                        pandaLiveBookMarkTab.setFocusable(true);
                        pandaLiveBookMarkTab.setFocusableInTouchMode(true);
                        pandaLiveBookMarkTab.requestFocus();
                    }
                    pandaLiveMainPager.setLayoutParams(layoutParams);
                } else if (tab.getPosition() == 0) {
                    ViewGroup.LayoutParams layoutParams = pandaLiveMainPager.getLayoutParams();
                    layoutParams.height = 1800;
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
                    pandaLiveMainPager.setLayoutParams(layoutParams);
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
                if(sureFirst == false) {
                    hand.sendEmptyMessage(1000);
                    sureFirst = true;
                }else {
                    Intent intent = new Intent();
                    intent.setAction("can.refresh");
                    intent.putExtra("refresh",true);
                    getActivity().sendBroadcast(intent);
                    hand.sendEmptyMessage(2000);
                }
            Log.i("abc","pandaLiveMainFragment==="+this);

        }
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
        new PandaLivePresent(multiAngleFragment);

        WatchChatFragment watchChatFragment = new WatchChatFragment();
//        Bundle bundle1 = new Bundle();
//        String url1 = bean.getBookmark().getWatchTalk().get(0).getUrl();
//        bundle1.putString("wathch_url", url1);
//        LogUtils.setLog("plmf", url1);
//        multiAngleFragment.setArguments(bundle1);

        list.add(bean.getBookmark().getMultiple().get(0).getTitle());
        list.add(bean.getBookmark().getWatchTalk().get(0).getTitle());

        mainLiveFragments.add(multiAngleFragment);
        mainLiveFragments.add(watchChatFragment);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setLiveTabBean(LiveTabBean bean) {

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
