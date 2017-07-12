package com.example.pandas.networks;


import com.example.pandas.networks.mycallbacks.NetCallbacks;

import java.util.Map;

/**
 * Created by 联想 on 2017/7/12.
 */

public interface IHttp {
    /**
     * 进行GET请求
     * @param url
     * @param pamars
     * @param callback
     * @param <T>
     */
    <T> void get(String url, Map<String, String> pamars, NetCallbacks callback);
    /**
     * 进行POST请求
     * @param url
     * @param pamars
     * @param callback
     * @param <T>
     */
    <T> void post(String url, Map<String, String> pamars, NetCallbacks callback);

    /**
     * 文件上传
     */
    void upload();

    /**
     * 文件下载
     */
    void download();

    /**
     * 进行图片请求
     */
    void imageLoad();
}
