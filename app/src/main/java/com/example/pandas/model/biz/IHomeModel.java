package com.example.pandas.model.biz;


import com.example.pandas.model.datebean.PageBean;
import com.example.pandas.model.datebean.pandasending.SendingBean;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */

public interface IHomeModel extends BaseModel{
   void getHomePageBean(NetCallbacks<PageBean> callbacks);
   void getSendingBean(NetCallbacks<SendingBean> callbacks);
}
