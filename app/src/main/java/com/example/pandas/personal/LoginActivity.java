package com.example.pandas.personal;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.personal.beans.LoginBean;
import com.example.pandas.personal.beans.NiChengBean;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginActivity extends BaseActivity {

    private static final int MSG_LGOIN_IN_GET_NICKNAME = 200;
    private static final int MSG_LOGIN_IN_ERROR = 300;
    private static final int MSG_GET_NICKNAME_SUCCESS = 400;
    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.weixin)
    ImageView weixin;
    @Bind(R.id.qq)
    ImageView qq;
    @Bind(R.id.sina)
    ImageView sina;
    @Bind(R.id.forget)
    TextView forget;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_pwd)
    EditText loginPwd;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getUserTicket();getUserTicket();
            switch (msg.what){
                case 300:
                    getUserTicket();
                break;

                case 400:
                    Log.e("TAG","--------"+nickname1);
                    SharedPreferences xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
                    SharedPreferences.Editor edit = xinxi.edit();
                    edit.putBoolean("boolean", false);
//                    edit.putString("iconurl", substring);
                    edit.putString("name", nickname1);
                    edit.commit();
                    break;
            }
        }
    };
    private String usrid;
    private String JSESSIONID = null;
    private String nickname1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initview() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @OnClick({R.id.fanhui, R.id.register, R.id.weixin, R.id.qq, R.id.sina, R.id.forget, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.weixin:
                break;
            case R.id.qq:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.sina:

                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            case R.id.forget:
                startActivity(new Intent(LoginActivity.this, UpdateActivity.class));
                break;
            case R.id.login:
                getLogin();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            String iconurl = data.get("iconurl");
            String name = data.get("name");
            Log.e("TAG", iconurl);
            Log.e("TAG", name);
            Intent intent = new Intent(LoginActivity.this, PersonalCenterActivity.class);
//            intent.putExtra();
//            intent.putExtra();
            startActivity(intent);
            SharedPreferences xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
            SharedPreferences.Editor edit = xinxi.edit();
            edit.putBoolean("boolean", false);
            edit.putString("iconurl", iconurl);
            edit.putString("name", name);
            edit.commit();
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


    public void getLogin() {

        OkHttpClient client = new OkHttpClient();

        String userName = loginName.getText().toString().trim();
        String passWard = loginPwd.getText().toString().trim();

        //http://cbox_mobile.regclientuser.cntv.cn
        String from = "https://reg.cntv.cn/login/login.action";
        try {
            String url = from + "?username="
                    + URLEncoder.encode(userName, "UTF-8")
                    + "&password=" + passWard
                    + "&service=client_transaction" + "&from="
                    + URLEncoder.encode(from, "UTF-8");

            FormBody formBody = new FormBody.Builder().build();

            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"))
                    .post(formBody)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("TAG","-----"+e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    Headers headers = response.headers();

                    JSESSIONID = headers.get("Set-Cookie");

                    Log.e("TAG", "Cookie+++++" + JSESSIONID);

                    String string = response.body().string();

                    Log.e("TAG","+++++"+string);

                    Gson gson = new Gson();

                    LoginBean loginBean = gson.fromJson(string, LoginBean.class);

                    String errType = loginBean.getErrType();

                    usrid = loginBean.getUsrid();

                    if (errType.equals("0")) {
                        handler.sendEmptyMessage(MSG_LGOIN_IN_GET_NICKNAME);
                    } else {

                        String errMsg = loginBean.getErrMsg();

                        Message message = handler.obtainMessage();

                        message.obj = errMsg;
                        message.what = MSG_LOGIN_IN_ERROR;




                        handler.sendMessage(message);
                    }
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }




    //获取昵称
    public void getUserTicket(){
        OkHttpClient client = new OkHttpClient();
        String form = "http://cbox_mobile.regclientuser.cntv.cn";
        String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                + "cbox_mobile" + "&method=" + "user.getNickName"
                + "&userid=" + usrid;

//        httpGet.addHeader("Referer",
//                URLEncoder.encode(client, "UTF-8"));
//        httpGet.addHeader("User-Agent", URLEncoder.encode(
//                "CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
//        httpGet.addHeader("Cookie", "verifycode=" + verifycode);

        try {
            Request request = new Request.Builder()
                    .addHeader("Referer",URLEncoder.encode(form, "UTF-8"))
                    .addHeader("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie",JSESSIONID)
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("TAG",e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();

                    Log.e("TAG","昵称请Josn::"+string);
                    Gson gson = new Gson();

                    NiChengBean niChengBean = gson.fromJson(string, NiChengBean.class);

                    nickname1 = niChengBean.getContent().getNickname();

//                    substring = nickname1.substring(1);

                    Log.e("TAG","--------"+nickname1);
//                    Log.e("TAG","--------"+substring);

                    handler.sendEmptyMessage(MSG_GET_NICKNAME_SUCCESS);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    }
