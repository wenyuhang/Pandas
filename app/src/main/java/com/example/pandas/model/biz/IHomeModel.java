package com.example.pandas.model.biz;


import com.example.pandas.model.datebean.RollvideoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.homebean.InteractiveInfoBean;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.model.datebean.pandasending.LiveTabBean;
import com.example.pandas.model.datebean.pandasending.MultipleBean;
import com.example.pandas.model.datebean.pandasending.OtherTabDetail;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.model.datebean.pandasending.WatchChatBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;

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

    //熊猫播报
    void getPdBBean(String path, String primary_id, String serviceId, NetCallbacks<PdBBean> callbacks);
    //   直播中国
    void getLiveChinaBean(String str,NetCallbacks<LiveChinaBean> callbacks);

    //直播中国详情
    void getSeneryBean(NetCallbacks<SceneryBean> callbacks);


    void getRollVideoBean(NetCallbacks<RollvideoBean> callbacks);

    //互动首页
    void getInteractiveBeans(NetCallbacks<InteractiveInfoBean> callbacks);

//    void getLiveTabBean(NetCallbacks<LiveTabBean> callbacks);

    //   直播 其他 tab  类
    void getOtherDetailBean(String vsid, String n, String serviceId, String o, String of, String p,NetCallbacks<OtherTabDetail> callbacks);

    //    多视角直播
    void getMultipleBean(NetCallbacks<MultipleBean> callbacks);

    //    边看边聊数据
    void getWatchChatBean(String app, String itemId, String nature, String page, NetCallbacks<WatchChatBean> callbacks);
}
