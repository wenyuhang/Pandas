package com.example.pandas.utils;


import android.util.Log;

public class LogUtils {
    static boolean flg = true;

    public static int setLog(String tag,String content){
        if(flg) {

            return Log.e(tag,content);
        }else {
            return 0;
        }
    }
}
