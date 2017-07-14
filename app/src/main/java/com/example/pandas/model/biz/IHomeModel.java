package com.example.pandas.model.biz;


import com.example.pandas.model.datebean.RollvideoBean;
import com.example.pandas.model.datebean.homebean.HomePageBean;
import com.example.pandas.model.datebean.pandabroadcastbean.PdBBean;
import com.example.pandas.model.datebean.pandabroadcastbean.TitleBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */

public interface IHomeModel extends BaseModel{
   // 首页
   void getHomePageBean(NetCallbacks<HomePageBean> callbacks);
   //   直播首页 类
   void getSendingBean(NetCallbacks<SendingBean> callbacks);

   //熊猫播报title
   void getTitleBean(NetCallbacks<TitleBean> callbacks);

   //熊猫播报
   void getPdBBean(String path, String primary_id, String serviceId, NetCallbacks<PdBBean> callbacks);
   //   直播中国
   void getLiveChinaBean(NetCallbacks callbacks);

   void getRollVideoBean(NetCallbacks<RollvideoBean> callbacks);
}
