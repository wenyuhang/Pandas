package com.example.pandas;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pandas.base.BaseActivity;
import com.example.pandas.homes.homelivechina.LiveChinaMain;
import com.example.pandas.homes.homelivechina.LiveChinaPresenter;
import com.example.pandas.homes.homepage.PageMain;
import com.example.pandas.homes.homepage.PagePresenter;
import com.example.pandas.homes.homepandabroadcast.PandaBroadcastMain;
import com.example.pandas.homes.homepandabroadcast.PandaBroadcastPresenter;
import com.example.pandas.homes.homepandalive.PandaLiveMain;
import com.example.pandas.homes.homerollvideo.RollVideoMain;
import com.example.pandas.homes.homerollvideo.RollVideoPresenter;

import butterknife.Bind;
import butterknife.OnClick;


//3-5
//lyh
//w1
//ly
public class HomeActivity extends BaseActivity {

    @Bind(R.id.home_page)
    RadioButton homePage;
    @Bind(R.id.home_PandaLive)
    RadioButton homePandaLive;
    @Bind(R.id.home_RollVideo)
    RadioButton homeRollVideo;
    @Bind(R.id.home_PandaBroadcast)
    RadioButton homePandaBroadcast;
    @Bind(R.id.home_LiveChina)
    RadioButton homeLiveChina;
    @Bind(R.id.home_radiogroup)
    RadioGroup homeRadiogroup;
    @Bind(R.id.activity_home)
    LinearLayout activityHome;
    @Bind(R.id.home_framelayout)
    FrameLayout homeFramelayout;
    private PageMain pageMain;
    private PandaLiveMain pandaLiveMain;
    private RollVideoMain rollVideoMain;
    private PandaBroadcastMain pandaBroadcastMain;
    private LiveChinaMain liveChinaMain;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    /**
     * 初始化Fragment数据  添加回退栈
     */
    @Override
    public void initview() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        pageMain = new PageMain();
        new PagePresenter(pageMain);
        transaction.add(R.id.home_framelayout, pageMain, PageMain.class.getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();

    }



    @OnClick({R.id.home_page, R.id.home_PandaLive, R.id.home_RollVideo, R.id.home_PandaBroadcast, R.id.home_LiveChina, R.id.home_radiogroup})
    public void onViewClicked(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        getpanduan(transaction);
        switch (view.getId()) {
            case R.id.home_page:
                    transaction.show(pageMain);
                break;
            case R.id.home_PandaLive:
                if (pandaLiveMain == null) {
                    pandaLiveMain = new PandaLiveMain();
                    transaction.add(R.id.home_framelayout, pandaLiveMain, PandaLiveMain.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(pandaLiveMain);
                }

                break;
            case R.id.home_RollVideo:
                if (rollVideoMain == null) {
                    rollVideoMain = new RollVideoMain();
                    new RollVideoPresenter(rollVideoMain);
                    transaction.add(R.id.home_framelayout, rollVideoMain, RollVideoMain.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(rollVideoMain);
                }
                break;
            case R.id.home_PandaBroadcast:
                if (pandaBroadcastMain == null) {
                    pandaBroadcastMain = new PandaBroadcastMain();
                    new PandaBroadcastPresenter(pandaBroadcastMain);
                    transaction.add(R.id.home_framelayout, pandaBroadcastMain, PandaBroadcastMain.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(pandaBroadcastMain);
                }
                break;
            case R.id.home_LiveChina:
                if (liveChinaMain == null) {
                    liveChinaMain = new LiveChinaMain();
                    new LiveChinaPresenter(liveChinaMain);
                    transaction.add(R.id.home_framelayout, liveChinaMain, LiveChinaMain.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(liveChinaMain);
                }
                break;
            case R.id.home_radiogroup:
                break;
        }
        transaction.commit();
    }

    public void getpanduan(FragmentTransaction transaction) {
        if (pageMain != null) {
            transaction.hide(pageMain);
        }
        if (pandaLiveMain != null) {
            transaction.hide(pandaLiveMain);
        }
        if (rollVideoMain != null) {
            transaction.hide(rollVideoMain);
        }
        if (pandaBroadcastMain != null) {
            transaction.hide(pandaBroadcastMain);
        }
        if (liveChinaMain != null) {
            transaction.hide(liveChinaMain);
        }
    }
}
