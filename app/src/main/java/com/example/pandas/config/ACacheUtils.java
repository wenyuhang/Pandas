package com.example.pandas.config;

import com.example.pandas.wxapi.App;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by 联想 on 2017/7/24.
 */

public class ACacheUtils {
    private static ACacheUtils utils;
    private final ArrayList<CollectionDate> dates;

    private ACacheUtils() {
        dates = new ArrayList<>();
    }
    public static ACacheUtils getUtils() {
        if(utils==null){
            synchronized (ACacheUtils.class){
                if(utils==null) {
        utils = new ACacheUtils();
                }
            }
        }
        return utils;
    }

    public void setStorage(String imageurl, String movietime, String moviename, String moviedate, String movieurl, String movieotherurl) {
        dates.add(new CollectionDate(imageurl,movietime,moviename,moviedate,movieurl,movieotherurl));
        ACache aCache = ACache.get(App.context);
        aCache.put("array",dates);
    }
}