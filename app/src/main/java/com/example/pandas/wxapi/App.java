package com.example.pandas.wxapi;

import android.app.Application;
import android.content.Context;

import com.example.pandas.base.BaseActivity;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by 联想 on 2017/7/12.
 */


public class App extends Application{
    public static BaseActivity context=null;
    private static Context mContext;


    {
        PlatformConfig.setWeixin("wx967daebe835fbeac","5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106216909","ghMT4dVEBRqgcTjK");
        PlatformConfig.setSinaWeibo("1755815889", "992f6038c59bf333f5f32c632284aae0","http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext =this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Config.DEBUG=true;
        UMShareAPI.get(this);

//        设置异常处理，程序才能获取未处理的异常
//        CrashHandler handler = CrashHandler.getInstance();
//        handler.init(this);
    }
    public static Context getContext(){
        return mContext;
    }

}
