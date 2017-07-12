package com.example.apps.model.biz;

import com.example.apps.config.Urls;
import com.example.apps.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */

public class IHomeImpl implements IHomeModel{

    @Override
    public void getHomePageBean(NetCallbacks<com.example.apps.model.datebean.PageBean> callbacks) {
        ihttp.get(Urls.CESHI,null,callbacks);
    }
}
