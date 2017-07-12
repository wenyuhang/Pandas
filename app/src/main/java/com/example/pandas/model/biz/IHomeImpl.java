package com.example.pandas.model.biz;


import com.example.pandas.config.Urls;
import com.example.pandas.model.datebean.PageBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */

public class IHomeImpl implements IHomeModel{

    @Override
    public void getHomePageBean(NetCallbacks<PageBean> callbacks) {
        ihttp.get(Urls.CESHI,null,callbacks);
    }

    /**
     * 获取熊猫直播中的首页
     * @param callbacks
     */
    @Override
    public void getSendingBean(NetCallbacks<SendingBean> callbacks) {
        ihttp.get(Urls.PANDALIVETAB,null,callbacks);
    }
}
