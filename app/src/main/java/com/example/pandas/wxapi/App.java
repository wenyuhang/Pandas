package com.example.pandas.wxapi;

import android.app.Application;

import com.example.pandas.base.BaseActivity;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by 联想 on 2017/7/12.
 */

public class App extends Application{
    public static BaseActivity context=null;



    {
        PlatformConfig.setWeixin("wx967daebe835fbeac","5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106216909","ghMT4dVEBRqgcTjK");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        UMShareAPI.get(this);
    }


}
