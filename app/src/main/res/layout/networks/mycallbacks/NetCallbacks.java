package com.example.apps.networks.mycallbacks;

/**
 * Created by 联想 on 2017/7/12.
 */

public interface NetCallbacks<T> {
    void onSuccess(T t);
    void onError(String errorMsg);
}
