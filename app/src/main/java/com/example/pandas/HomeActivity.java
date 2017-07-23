package com.example.pandas;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.base.BaseActivity;
import com.example.pandas.homes.homelivechina.LiveChinaMain;
import com.example.pandas.homes.homelivechina.LiveChinaPresenter;
import com.example.pandas.homes.homepage.PageMain;
import com.example.pandas.homes.homepage.PagePresenter;
import com.example.pandas.homes.homepandabroadcast.PandaBroadcastMain;
import com.example.pandas.homes.homepandabroadcast.PandaBroadcastPresenter;
import com.example.pandas.homes.homepandalive.PandaLiveMain;
import com.example.pandas.homes.pandaculture.PandaCultureFragment;
import com.example.pandas.homes.personalcenter.PersonalMain;
import com.example.pandas.personal.homeinteractive.InteractiveMainActivity;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


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
    private int x = 0;
    private PersonalMain personalMain;
    private int a = 1;
    private int b=2;


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
        a = 1;
    }



    @OnClick({R.id.home_page, R.id.home_PandaLive, R.id.home_RollVideo, R.id.home_PandaBroadcast, R.id.home_LiveChina, R.id.home_radiogroup, R.id.home_person, R.id.home_interaction})
    public void onViewClicked(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        getpanduan(transaction);
        homeRadiogroup.setVisibility(View.VISIBLE);
        switch (view.getId()) {
            case R.id.home_page:
                a = 1;
                transaction.show(pageMain);
                homeIcon.setVisibility(View.VISIBLE);
                homeText.setVisibility(View.GONE);
                homeInteraction.setVisibility(View.VISIBLE);
                break;
            case R.id.home_PandaLive:
                a = 2;
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
                a = 3;
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
                a = 4;
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
                a = 5;
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
                b = 0;
                homeText.setText("个人中心");
                homeRadiogroup.setVisibility(View.GONE);
                if (personalMain == null) {
                    personalMain = new PersonalMain();
//                    new LiveChinaPresenter(personalMain);
                    transaction.add(R.id.home_framelayout, personalMain, PersonalMain.class.getSimpleName());
                    transaction.addToBackStack(null);
                } else {
                    transaction.show(personalMain);
                }
                break;
            case R.id.home_interaction:
                startActivity(new Intent(this, InteractiveMainActivity.class));
                break;
        }
        x=0;
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
        if (personalMain != null) {
            transaction.hide(personalMain);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        x++;
        Log.d("HomeActivity", x + "-------");

        if (b == 0) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(personalMain);
            homeIcon.setVisibility(View.GONE);
            homeText.setVisibility(View.VISIBLE);
            homeInteraction.setVisibility(View.GONE);
            switch (a) {
                case 1:
                    homeIcon.setVisibility(View.VISIBLE);
                    homeText.setVisibility(View.GONE);
                    homeInteraction.setVisibility(View.VISIBLE);
                    transaction.show(pageMain);

                    break;
                case 2:
                    homeText.setText("熊猫直播");
                    transaction.show(pandaLiveMain);
                    break;
                case 3:
                    homeText.setText("熊猫文化");
                    transaction.show(pandaCultureFragment);
                    break;
                case 4:
                    homeText.setText("熊猫观察");
                    transaction.show(pandaBroadcastMain);
                    break;
                case 5:
                    homeText.setText("直播中国");
                    transaction.show(liveChinaMain);
                    break;
            }
            x = 0;
            b = 1;
            homeRadiogroup.setVisibility(View.VISIBLE);
            transaction.commit();
        }




        if (JCVideoPlayer.backPress()) {
            return;
        }
        if (x == 1) {
            Toast.makeText(this, "连续点击两次退出应用", Toast.LENGTH_SHORT).show();

        }

        if (x == 2) {

            finish();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        x = 0;
    }
}
