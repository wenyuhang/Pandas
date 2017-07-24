package com.example.pandas.model.biz;


import com.example.pandas.config.Urls;
import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.model.datebean.UpDateBean;
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;
import com.example.pandas.model.datebean.pandabroadcastbean.BandaBroadBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.PlayBean;
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
    public void getLiveChinaBean(String str,NetCallbacks callbacks) {
        ihttp.get(str , null, callbacks);
    }

    /**
     *
     *获取tab详情页面
     * @param callbacks
     */
    @Override
    public void getSeneryBean(NetCallbacks<SceneryBean> callbacks) {
        ihttp.get(Urls.LIVECHINA,null,callbacks);
    }

    //熊猫文化
    @Override
    public void getRollVideoBean(NetCallbacks<PandaCultureEntity> callbacks) {
        ihttp.get(Urls.RELLOVIDEO,null,callbacks);
    }

    @Override
    public void getInteractiveBeans(NetCallbacks<InteractiveInfoBean> callbacks) {
        ihttp.get(Urls.INTERACTIVE,null,callbacks);
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

    @Override
    public void getPandaPlayBean(String pid,NetCallbacks<PlayBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        ihttp.get(Urls.PANDAVOD,map,callbacks);
    }


    //    熊猫文化详情的CCTVvideo
    public void getCCTVVideo(String n, String vsid, String p , String serviceId,String em, NetCallbacks<CCTVBaen> callbacks){
        Map<String,String> map = new HashMap<>();
        map.put("n",n);
        map.put("vsid",vsid);
        map.put("p",p);
        map.put("serviceId",serviceId);
        map.put("em",em);
        ihttp.get(Urls.CCTVVIDEO,map,callbacks);
    }
    //熊猫文化获取视频播放地址
    @Override
    public void getVideoUrl(String pid, NetCallbacks<PlayVideo> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }
    //    熊猫文化全屏播放的
    @Override
    public void getStartVideo(String pid, NetCallbacks<VideoStartBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getPandaEyeBean(String url,NetCallbacks<PandaEyeListBean> callbacks) {
        ihttp.get(url,null,callbacks);
    }

    @Override
    public void getCCTVBean(String url, NetCallbacks<CCTVInfoBean> callbacks) {
        ihttp.get(url,null,callbacks);
    }

    @Override
    public void getLightChinaBean(String url, NetCallbacks<LightChinaBean> callbacks) {
        ihttp.get(url,null,callbacks);
    }

    @Override
    public void getRotationBean(String id, NetCallbacks<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getWonderfulBean(String id, NetCallbacks<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getItemBean(String id, NetCallbacks<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getPandaWatchBean(String id, NetCallbacks<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getLightChinaVideoInfo(String id, NetCallbacks<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        ihttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getUpDateBean(NetCallbacks<UpDateBean> callbacks) {
        ihttp.get(Urls.VERSIONCODE,null,callbacks);
    }
//    熊猫观察里面视频url的请求
    public void getBroadVideoUrl(String pid, NetCallbacks<BandaBroadBean> callbacks){
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        ihttp.get(Urls.BROADPLAYVIDEO,map,callbacks);

    }
}
