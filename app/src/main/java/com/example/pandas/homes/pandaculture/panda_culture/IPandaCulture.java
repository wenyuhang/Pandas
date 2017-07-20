package com.example.pandas.homes.pandaculture.panda_culture;


import com.example.pandas.model.biz.BaseModel;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by hp1 on 2017-04-07.
 */

public interface IPandaCulture extends BaseModel {

    void getPandaCultureHead(String url, NetCallbacks myCallback);
    void getPandaCultureItem(String url, NetCallbacks myCallback);
}
