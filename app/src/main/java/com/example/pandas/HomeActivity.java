package com.example.pandas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
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
import com.example.pandas.model.datebean.UpDateBean;
import com.example.pandas.personal.homeinteractive.InteractiveMainActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


//3-5
//lyh
//w1
//ly
public class HomeActivity extends BaseActivity implements HomeContract.View{

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
    private HomeContract.Presenter presenter;
    private int x = 0;
    private PersonalMain personalMain;
    private int a = 1;
    private int b=2;
    private static int versionCode;
    private String versionsUrl;
    private AlertDialog alertDialog;
    private  int total = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    /**
     * 初始化Fragment数据  添加回退栈
     */
    @Override
    public void initview() {
        new HomePresenter(this);
        presenter.strat();
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
                transaction.commit();
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
                transaction.commit();
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
                transaction.commit();
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
                transaction.commit();
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
                transaction.commit();
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
                transaction.commit();
                break;
            case R.id.home_interaction:
                startActivity(new Intent(this, InteractiveMainActivity.class));
                break;
        }
        x=0;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        homeIcon.setVisibility(View.VISIBLE);
        homeText.setVisibility(View.GONE);
        homeInteraction.setVisibility(View.VISIBLE);
        presenter.strat();
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
            presenter.strat();
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

    @Override
    public void setResult(UpDateBean netBean) {
        getAppVersionName(this);
        UpDateBean.DataBean data = netBean.getData();
        String getVersionsNum = data.getVersionsNum();
        int VersionsNum = Integer.parseInt(getVersionsNum);
        String versionsUrl1 = data.getVersionsUrl();
        versionsUrl=versionsUrl1;
        if(versionCode<VersionsNum){
            showDialogUpdate();
        }else {
            Toast.makeText(HomeActivity.this, "已经是最新版本", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter=presenter;
    }

    //获取当前版本
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            versionCode = pi.versionCode;
            Log.e("aaa", versionCode + "");
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("aaa", versionName);
        }
        return versionName;

    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置要显示的信息
                        setMessage("发现新版本  修复了已知的BUG").
                // 设置确定按钮
                        setPositiveButton("现在去更新", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        dialog.dismiss();
                        loadNewVersionProgress();//下载最新的版本程序

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("以后再说", null);

        // 生产对话框
        alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }

    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final String uri = versionsUrl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Log.e("abc", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength()/1024/1024);
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");


            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                pd.setProgress(total/1024/1024);
            }
            Log.d("HomeActivity", "total:" + total);
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
