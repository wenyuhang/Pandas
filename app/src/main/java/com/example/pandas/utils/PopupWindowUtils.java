package com.example.pandas.utils;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.example.pandas.R;

public class PopupWindowUtils {
    private static PopupWindowUtils utils;
    private PopupWindow popupWindow;
    private ProgressBar bar;
    private Context context;
    private View view;

    private PopupWindowUtils(Context context){
        this.context = context;
    }

    public static PopupWindowUtils getInstance(Context context){
        if(utils == null) {
            synchronized (PopupWindowUtils.class){
                utils = new PopupWindowUtils(context);
            }
        }

        return utils;
    }

    private void initPopup(){
        view = LayoutInflater.from(context).inflate(R.layout.popup_anim,null);

        bar = (ProgressBar) view.findViewById(R.id.progressbar_anim);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT,true);
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.alph_gray));
        popupWindow.setOutsideTouchable(false);
    }
//    开始
    public void startPopup(){
        initPopup();
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
    }

//    结束
    public void stopPopup(){
        popupWindow.dismiss();
    }
}
