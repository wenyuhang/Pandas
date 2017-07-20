package com.example.pandas.homes.pandaculture.panda_culture;

import com.example.pandas.config.Urls;
import com.example.pandas.networks.HttpFactroy;
import com.example.pandas.networks.mycallbacks.NetCallbacks;

/**
 * Created by hp1 on 2017-04-07.
 */

public class PandaCultureImp implements IPandaCulture {
    @Override
    public void getPandaCultureHead(String url, NetCallbacks myCallback) {
        HttpFactroy.createOkhttp().get(Urls.RELLOVIDEO,null,myCallback);
    }

    @Override
    public void getPandaCultureItem(String url, NetCallbacks myCallback) {
        HttpFactroy.createOkhttp().get(Urls.RELLOVIDEO,null,myCallback);
    }
}
