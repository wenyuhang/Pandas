package com.example.pandas.networks;

import com.example.pandas.app.App;
import com.example.pandas.networks.mycallbacks.NetCallbacks;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 联想 on 2017/7/12.
 */

public class OkHttpUtils implements IHttp {
    private static OkHttpUtils utils;
    private final OkHttpClient okHttpClient;

    private OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static OkHttpUtils getInstance(){
        if(utils==null){
            synchronized (OkHttpUtils.class){
                if(utils==null) {
                    utils=new OkHttpUtils();
                }
            }
        }
        return utils;
    }

    @Override
    public <T> void get(String url, Map<String, String> params, final NetCallbacks callback) {
        StringBuffer buffer = new StringBuffer(url);
        if(params!=null&&params.size()>0){
            buffer.append("?");
            Set<String> keys = params.keySet();
            for(String key:keys){
                String value = params.get(key);
                buffer.append(key).append("=").append(value).append("&");
            }
            url=buffer.deleteCharAt(buffer.length()-1).toString();
        }

        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(getGeneric(json,callback));
                    }
                });
            }
        });
    }

    @Override
    public <T> void post(String url, Map<String, String> pamars, NetCallbacks callback) {

    }

    @Override
    public void upload() {

    }

    @Override
    public void download() {

    }

    @Override
    public void imageLoad() {

    }

    /**
     * 自动解析json至回调中的JavaBean
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getGeneric(String jsonData,NetCallbacks<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData,type);
        return t;
    }
}
