package com.example.pandas;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pandas.base.BaseActivity;
import com.example.pandas.homes.homelivechina.LiveChinaMain;
import com.example.pandas.homes.homelivechina.LiveChinaPresenter;
import com.example.pandas.homes.homepage.PageMain;
import com.example.pandas.homes.homepage.PagePresenter;
import com.example.pandas.homes.homepandabroadcast.PandaBroadcastMain;
import com.example.pandas.homes.homepandabroadcast.PandaBroadcastPresenter;
import com.example.pandas.homes.homepandalive.PandaLiveMain;
import com.example.pandas.homes.pandaculture.PandaCultureFragment;
import com.example.pandas.personal.PersonalCenterActivity;
import com.example.pandas.personal.homeinteractive.InteractiveMainActivity;

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
    @Bind(R.id.home_framelayout)
    FrameLayout homeFramelayout;
    @Bind(R.id.home_icon)
    ImageView homeIcon;
    @Bind(R.id.home_person)
    ImageView homePerson;
    @Bind(R.id.home_text)
    TextView homeText;
    @Bind(R.id.home_interaction)
    ImageView homeInteraction;
    @Bind(R.id.activity_home)
    RelativeLayout activityHome;
    private PageMain pageMain;
    private PandaLiveMain pandaLiveMain;
    private PandaBroadcastMain pandaBroadcastMain;
    private LiveChinaMain liveChinaMain;
    private PandaCultureFragment pandaCultureFragment;


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


    @OnClick({R.id.home_page, R.id.home_PandaLive, R.id.home_RollVideo, R.id.home_PandaBroadcast, R.id.home_LiveChina, R.id.home_radiogroup,R.id.home_person,R.id.home_interaction})
    public void onViewClicked(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        getpanduan(transaction);
        switch (view.getId()) {
            case R.id.home_page:
                transaction.show(pageMain);
                homeIcon.setVisibility(View.VISIBLE);
                homeText.setVisibility(View.GONE);
                homeInteraction.setVisibility(View.VISIBLE);
                break;
            case R.id.home_PandaLive:
                homeText.setText("熊猫直播");
                if (pandaLiveMain == null) {
                    pandaLiveMain = new PandaLiveMain();
                    transaction.add(R.id.home_framelayout, pandaLiveMain, PandaLiveMain.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(pandaLiveMain);
                }

                break;
            case R.id.home_RollVideo:
                homeText.setText("熊猫文化");
                if (pandaCultureFragment == null) {
                    pandaCultureFragment = new PandaCultureFragment();
                    transaction.add(R.id.home_framelayout, pandaCultureFragment, PandaCultureFragment.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(pandaCultureFragment);
                }
                break;
            case R.id.home_PandaBroadcast:
                homeText.setText("熊猫观察");
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
                homeText.setText("直播中国");
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
            case R.id.home_person:
                startActivity(new Intent(this, PersonalCenterActivity.class));
                break;
            case R.id.home_interaction:
                startActivity(new Intent(this, InteractiveMainActivity.class));
                break;
        }
        transaction.commit();
    }

    public void getpanduan(FragmentTransaction transaction) {
        homeIcon.setVisibility(View.GONE);
        homeText.setVisibility(View.VISIBLE);
        homeInteraction.setVisibility(View.GONE);
        if (pageMain != null) {
            transaction.hide(pageMain);
        }
        if (pandaLiveMain != null) {
            transaction.hide(pandaLiveMain);
        }
        if (pandaCultureFragment != null) {
            transaction.hide(pandaCultureFragment);
        }
        if (pandaBroadcastMain != null) {
            transaction.hide(pandaBroadcastMain);
        }
        if (liveChinaMain != null) {
            transaction.hide(liveChinaMain);
        }
    }

}
