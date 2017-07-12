package com.example.apps.networks;

/**
 * Created by 联想 on 2017/7/12.
 */

public class HttpFactroy {
    public static com.example.apps.networks.IHttp createOkhttp(){
        return OkHttpUtils.getInstance();
    }

}
