package com.example.pandas.networks;

/**
 * Created by 联想 on 2017/7/12.
 */

public class HttpFactroy {
    public static IHttp createOkhttp(){
        return OkHttpUtils.getInstance();
    }

}
