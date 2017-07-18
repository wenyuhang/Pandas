package com.example.pandas.config;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/7/17.
 */

public class GsonUtiles {
    private static GsonUtiles gsonUtiles;
    private Gson gson;
    private  GsonUtiles(){
        gson = new Gson();
    }

    public static GsonUtiles getInstance(){
        if(gsonUtiles==null){
            synchronized (GsonUtiles.class){
                if(gsonUtiles==null) {
                    gsonUtiles=new GsonUtiles();
                }
            }
        }
        return gsonUtiles;
    }
    public Gson getGson(){
        return gson;
    }
}
