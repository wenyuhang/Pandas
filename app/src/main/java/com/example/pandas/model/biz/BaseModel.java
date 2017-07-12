package com.example.pandas.model.biz;

import com.example.pandas.networks.HttpFactroy;
import com.example.pandas.networks.IHttp;

/**
 * Created by 联想 on 2017/7/12.
 */

public interface BaseModel {
    public static IHttp ihttp= HttpFactroy.createOkhttp();
}
