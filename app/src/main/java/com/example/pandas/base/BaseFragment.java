package com.example.pandas.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by 联想 on 2017/7/11.
 */

public abstract class BaseFragment extends Fragment {

    private EventBus build;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this,view);
//        setEventBus();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
//        build.unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //当fragment处于隐藏状态时
        if(hidden){
            onHidden();
        }else {
            //当前Fragment处于显示状态
            onShow();
        }
    }

    //加载布局
    protected abstract int getLayoutId();
    //初始化数据
    protected abstract void init(View view);
    //加载数据
    protected abstract void loadData();
    //该方法在Fragment可见时调用，可以在该方法中刷新数据
    protected void onShow(){}
    //该方法在Fragment隐藏时调用，可以做数据保存
    protected void onHidden(){}

    public EventBus setEventBus(boolean flg){
        if(flg) {
            build = EventBus.builder().eventInheritance(false).build();
            build.register(this);

        }
        return build;
    }
}
