package com.example.pandas.model.biz;


import com.example.pandas.homes.pandaculture.bean.CCTVBaen;
import com.example.pandas.homes.pandaculture.bean.PandaCultureEntity;
import com.example.pandas.homes.pandaculture.bean.PlayVideo;
import com.example.pandas.homes.pandaculture.bean.VideoStartBean;
import com.example.pandas.model.datebean.homebean.CCTVInfoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;
import com.example.pandas.model.datebean.homebean.LightChinaBean;
import com.example.pandas.model.datebean.homebean.PandaEyeListBean;
import com.example.pandas.model.datebean.homebean.VideoInfoBean;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.PlayBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */

public interface IHomeModel extends BaseModel{
    // 首页
    void getHomePageBean(NetCallbacks<HomePageBean> callbacks);
    //   直播首页 类
    void getSendingBean(NetCallbacks<SendingBean> callbacks);
    //   直播列表类
    void getLiveTabBean(NetCallbacks<LiveTabBean> callbacks);
    //熊猫播报title
    void getTitleBean(NetCallbacks<TitleBean> callbacks);

    //   直播中国
    void getLiveChinaBean(String str,NetCallbacks<LiveChinaBean> callbacks);

    //直播中国详情
    void getSeneryBean(NetCallbacks<SceneryBean> callbacks);
    //熊猫文化
    void getRollVideoBean(NetCallbacks<PandaCultureEntity> callbacks);

    //互动首页
    void getInteractiveBeans(NetCallbacks<InteractiveInfoBean> callbacks);

    //    void getLiveTabBean(NetCallbacks<LiveTabBean> callbacks);

    //   直播 其他 tab  类
    void getOtherDetailBean(String vsid, String n, String serviceId, String o, String of, String p,NetCallbacks<OtherTabDetail> callbacks);

    //    多视角直播
    void getMultipleBean(NetCallbacks<MultipleBean> callbacks);

    //    边看边聊数据
    void getWatchChatBean(String app, String itemId, String nature, String page, NetCallbacks<WatchChatBean> callbacks);

    void getPandaPlayBean(String pid,NetCallbacks<PlayBean> callbacks);
    //    熊猫文化详情的CCTVvideo
    void getCCTVVideo(String n, String vsid, String p , String serviceId,String em, NetCallbacks<CCTVBaen> callbacks);
    //    熊猫文化详情播放视频
    void getVideoUrl(String pid, NetCallbacks<PlayVideo> callbacks);
    //    熊猫文化全屏播放的
    void getStartVideo(String pid, NetCallbacks<VideoStartBean> callbacks);

    void getPandaEyeBean(String url,NetCallbacks<PandaEyeListBean> callbacks);

    void getCCTVBean(String url, NetCallbacks<CCTVInfoBean> callbacks);

    void getLightChinaBean(String url, NetCallbacks<LightChinaBean> callbacks);

    void getRotationBean(String id,NetCallbacks<VideoInfoBean> callbacks);

    void getWonderfulBean(String id,NetCallbacks<VideoInfoBean> callbacks);

    void getItemBean(String id,NetCallbacks<VideoInfoBean> callbacks);

    void getPandaWatchBean(String id,NetCallbacks<VideoInfoBean> callbacks);

    void getLightChinaVideoInfo(String id,NetCallbacks<VideoInfoBean> callbacks);
}
