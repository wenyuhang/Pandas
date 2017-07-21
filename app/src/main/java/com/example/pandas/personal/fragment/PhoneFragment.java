package com.example.pandas.personal.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.BuildConfig;
import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.personal.LoginActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by li on 2017/7/13.
 */

public class PhoneFragment extends BaseFragment {
    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.edit_imgyanzhengma)
    EditText editImgyanzhengma;
    @Bind(R.id.personal_reg_imgcheck)
    ImageView personalRegImgcheck;
    @Bind(R.id.edit_phoneyanzhengma)
    EditText editPhoneyanzhengma;
    @Bind(R.id.personal_reg_phonecheck)
    TextView personalRegPhonecheck;
    @Bind(R.id.edit_inputpasswrod)
    EditText editInputpasswrod;
    @Bind(R.id.xieyi_check)
    CheckBox xieyiCheck;
    @Bind(R.id.btn_register)
    TextView btnRegister;
    @Bind(R.id.null_phone)
    TextView nullPhone;
    @Bind(R.id.null_imagecheck)
    TextView nullImagecheck;
    @Bind(R.id.null_phonecheck)
    TextView nullPhonecheck;
    private String JSESSIONID = null;
    //    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what){
//                case 200:
//                    Drawable captchaImage = byteToDrawable(bytes);
//                    personalRegImgcheck.setBackgroundDrawable(captchaImage);
//                    personalRegImgcheck.setText("");
//                    break;
//            }
//
//        }
//    };
    private byte[] bytes;
    private String exception;
    private String phoneyanzhengma;
    private String passwrod;

    @Override
    protected int getLayoutId() {
        return R.layout.phonefragment;
    }

    @Override
    protected void init(View view) {
        Log.e("TAG", "666");
        getPersonalRegImgCheck();
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.personal_reg_imgcheck, R.id.personal_reg_phonecheck, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //图形验证码
            case R.id.personal_reg_imgcheck:
                getPersonalRegImgCheck();
                break;
            //获取验证码
            case R.id.personal_reg_phonecheck:
                String tPhoneNumber = editPhone.getText().toString().trim();
//                    图形验证码
                String imgyanzhengma = editImgyanzhengma.getText().toString().trim();

                    if (tPhoneNumber.equals("")&&tPhoneNumber==null&&imgyanzhengma.equals("")&&imgyanzhengma==null&&phoneyanzhengma.equals("")&&phoneyanzhengma==null) {
                            nullPhone.setText("手机号不能为空");
                            nullImagecheck.setText("验证码不能为空");
                            nullPhonecheck.setText("验证码不能为空");
                    }
                getPersonalRegPhoneCheck();
                break;
            //注册
            case R.id.btn_register:
                try {
                    getRegister();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                xieyiCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked==true) {
                            try {
                                getRegister();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(getActivity(), "未勾选《央视网网络服务使用协议》", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;
        }
    }

    public void getPersonalRegImgCheck() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request build = new Request.Builder().url("http://reg.cntv.cn/simple/verificationCode.action").build();
                okHttpClient.newCall(build).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Headers headers = response.headers();

                        JSESSIONID = headers.get("Set-Cookie");
                        Log.e("TAG", "图形验证码的额" + JSESSIONID);

//                            for (int x=0;x<headers.size();x++){
//                                String name = headers.name(x);
//                                String name1 = headers.get(name);
//                                if(name1.contains("JSESSIONID")) {
//                                    JSESSIONID=name1;
//                                    break;
//                                }
//                                Log.e("TAG",name1+"---+++");
//                            }

                        bytes = response.body().bytes();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Drawable captchaImage = byteToDrawable(bytes);
                                personalRegImgcheck.setImageDrawable(captchaImage);
                            }
                        });
                    }
                });
            }
        }).start();
    }


    public static Drawable byteToDrawable(byte[] byteArray) {
        try {
            String string = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
        return Drawable.createFromStream(ins, null);
    }


    public void getPersonalRegPhoneCheck() {
//17600304681


        OkHttpClient click = new OkHttpClient();
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
//                    手机号
        String tPhoneNumber = editPhone.getText().toString().trim();
//                    图形验证码
        String imgyanzhengma = editImgyanzhengma.getText().toString().trim();
//                请求  获取验证码的 网络请求
//                post 请求体
        Log.e("TAG", "图形验证码" + JSESSIONID);
        RequestBody body = new FormBody.Builder()
                .add("method", "getRequestVerifiCodeM")
                .add("mobile", tPhoneNumber)
                .add("verfiCodeType", "1")
                .add("verificationCode", imgyanzhengma)
                .build();
        try {
//                    post  请求头
            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie", JSESSIONID)
                    .post(body).build();

            click.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    exception = e.getMessage().toString();
                    Log.e("TAG", e.getMessage().toString());


                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();
                    Log.e("TAG", "手机验证码结果" + string);

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    public void getRegister() throws UnsupportedEncodingException {

        OkHttpClient client = new OkHttpClient();
//                    手机号
        String tPhoneNumber = editPhone.getText().toString().trim();
        Log.e("TAG", "手机号：" + tPhoneNumber);
//                    图形验证码
        String imgyanzhengma = editImgyanzhengma.getText().toString().trim();
        //手机验证码
        phoneyanzhengma = editPhoneyanzhengma.getText().toString().trim();
        Log.e("TAG", "手机验证码：" + phoneyanzhengma);
//mima
        passwrod = editInputpasswrod.getText().toString().trim();

        Log.e("TAG", "密码：" + passwrod);

//                请求  获取验证码的 网络请求
//                post 请求体
        Log.e("TAG", "图形验证码" + JSESSIONID);
        RequestBody body = new FormBody.Builder()
                .add("method", "saveMobileRegisterM")
                .add("mobile", tPhoneNumber)
                .add("verfiCode", phoneyanzhengma)
                .add("passWd", URLEncoder.encode(passwrod, "UTF-8"))
                .add("verfiCod eType", "1")
                .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                .build();

        Request build = new Request.Builder().url("https://reg.cntv.cn/regist/mobileRegist.do")
                .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                .post(body)
                .build();

        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if (BuildConfig.DEBUG) {
                    Log.e("TAG", e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String tString = response.body().string();
                if (BuildConfig.DEBUG) {
                    Log.e("TAG", "注册：" + tString);

                    if(tString.equals("Success")) {
                        Toast.makeText(getActivity(), "注册成功，返回注册", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }

                }
            }
        });

    }
}
