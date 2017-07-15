package com.example.pandas.model.biz;


import com.example.pandas.config.Urls;
import com.example.pandas.model.datebean.RollvideoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
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

    @Override
    public void getTitleBean(NetCallbacks<TitleBean> callbacks) {
        ihttp.get(Urls.PANDABROADCASTTITLE,null,callbacks);
    }


    /**
     * 获取直播中国中的首页
     *
     */
    @Override
    public void getLiveChinaBean(NetCallbacks callbacks) {

    }

    @Override
    public void getRollVideoBean(NetCallbacks<RollvideoBean> callbacks) {
        ihttp.get(Urls.RELLOVIDEO,null,callbacks);
    }

    @Override
    public void getPdBBean(String path, String primary_id, String serviceId, NetCallbacks<PdBBean> callbacks) {
        Map<String,String> map = new HashMap<>() ;
        map.put("path",path);
        map.put("primary_id",primary_id);
        map.put("serviceId",serviceId);
        ihttp.get(Urls.PANDABROADCAST,map,callbacks);
    }

    /**
     * 获取tab标题栏数据
     * @param callbacks
     */
    @Override
    public void getLiveTabBean(NetCallbacks<LiveTabBean> callbacks) {
        ihttp.get(Urls.PANDALIVETAB,null,callbacks);
    }

    @Override
    public void getOtherDetailBean(String vsid, String n, String serviceId, String o, String of, String p,NetCallbacks<OtherTabDetail> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("vsid",vsid);
        map.put("n",n);
        map.put("serviceId",serviceId);
        map.put("o",o);
        map.put("of",of);
        map.put("p",p);
        ihttp.get(Urls.BASEOTHERFragment,map,callbacks);
    }

    @Override
    public void getMultipleBean(NetCallbacks<MultipleBean> callbacks) {
        ihttp.get(Urls.PANDALIVEMULTI,null,callbacks);
    }

    @Override
    public void getWatchChatBean(String app, String itemId, String nature, String page, NetCallbacks<WatchChatBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("app",app);
        map.put("itemId",itemId);
        map.put("nature",nature);
        map.put("page",page);
        ihttp.get(Urls.WATCHCHATDATE,map,callbacks);
    }
}
