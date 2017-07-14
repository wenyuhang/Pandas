package com.example.pandas.model.biz;


import com.example.pandas.config.Urls;
import com.example.pandas.model.datebean.PdBBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 联想 on 2017/7/12.
 */

public class IHomeImpl implements IHomeModel{
    /**
     *  首页中的网络请求
     * @param callbacks
     */
    @Override
    public void getHomePageBean(NetCallbacks<HomePageBean> callbacks) {
        ihttp.get(Urls.HOMEPAGE,null,callbacks);
    }

    /**
     * 获取熊猫直播中的首页
     * @param callbacks
     */
    @Override
    public void getSendingBean(NetCallbacks<SendingBean> callbacks) {
        ihttp.get(Urls.PANDALIVE,null,callbacks);
    }


    /**
     * 获取直播中国中的首页
     *
     */
    @Override
    public void getLiveChinaBean(NetCallbacks callbacks) {

    }
    @Override
    public void getPdBBean(String path, String primary_id, String serviceId, NetCallbacks<PdBBean> callbacks) {
        Map<String,String> map = new HashMap<>() ;
        map.put("path",path);
        map.put("primary_id",primary_id);
        map.put("serviceId",serviceId);
        ihttp.get(Urls.PANDABROADCAST,map,callbacks);
    }
}
