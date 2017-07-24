package com.example.pandas.personal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalXinActivity extends BaseActivity implements UMAuthListener{

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.iv_headicon)
    ImageView ivHeadicon;
    @Bind(R.id.person_have_login_layout)
    RelativeLayout personHaveLoginLayout;
    @Bind(R.id.tv_nichengjiantou)
    TextView tvNichengjiantou;
    @Bind(R.id.personal_nickname_layout)
    RelativeLayout personalNicknameLayout;
    @Bind(R.id.btn_login_out)
    TextView btnLoginOut;
    @Bind(R.id.nick_name)
    TextView nickName;
    private Uri uriForCamera;
    private ArrayList<String> list=new ArrayList<>();
    private Date date;
    private String str;
    private File appDir;
    private SharedPreferences xinxi;
    private SharedPreferences.Editor edit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_xin;
    }

    @Override
    public void initview() {
//        String iconurl = getIntent().getStringExtra("iconurl");
//        String name = getIntent().getStringExtra("name");
        xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
        String iconurl = xinxi.getString("iconurl", "");
        String name = xinxi.getString("name", "");
        Log.d("PersonalXinActivity", iconurl + "--------" + name);
        Glide.with(this).load(iconurl).into(ivHeadicon);
        nickName.setText(name);
        edit = xinxi.edit();

        if(xinxi.getString("camera","")!=null) {
            String cameras = xinxi.getString("camera", "");
            Uri uri = Uri.parse(cameras);
            ivHeadicon.setImageURI(uri);
        }
    }

    @OnClick({R.id.fanhui, R.id.iv_headicon, R.id.person_have_login_layout, R.id.personal_nickname_layout, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.iv_headicon:
                break;
            case R.id.person_have_login_layout:
                View views= LayoutInflater.from(this).inflate(R.layout.popwindow_item,null);
                final PopupWindow window=new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                window.setOutsideTouchable(false);
                window.setBackgroundDrawable(new ColorDrawable(0xb0000000));
                window.setContentView(views);
                window.showAtLocation(views,Gravity.BOTTOM,0,0);
                window.setAnimationStyle(R.style.pop_takephoto);
                views.findViewById(R.id.photochoose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1000);
                        window.dismiss();
                    }
                });

                views.findViewById(R.id.takephoto).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        uriForCamera = Uri.fromFile(createImageStoragePath());
                        list.add(String.valueOf(uriForCamera));
                        intents.putExtra(MediaStore.EXTRA_OUTPUT, uriForCamera);
                        startActivityForResult(intents, 2000);
                        window.dismiss();
                    }
                });

                views.findViewById(R.id.cancels).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });

                break;
            case R.id.personal_nickname_layout:
                break;
            case R.id.btn_login_out:
                UMShareAPI.get(PersonalXinActivity.this).deleteOauth(PersonalXinActivity.this
                        , SHARE_MEDIA.QQ,this);
                UMShareAPI.get(PersonalXinActivity.this).deleteOauth(PersonalXinActivity.this
                        , SHARE_MEDIA.SINA,this);
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1000 :
                if(resultCode!=0){
                    Uri selectedImage = data.getData();
                    cropRawPhoto(selectedImage);
                    edit.putString("camera", String.valueOf(selectedImage));
                    edit.commit();
                }
                break;
            case 2000:
                if(resultCode!=0){
                    Uri uri = Uri.parse(list.get(0));
                    cropRawPhoto(uri);
                    edit.putString("camera", String.valueOf(uri));
                    edit.commit();
                }

                break;
            case 3000:
                Bundle extras = data.getExtras();
                if(resultCode!=0){
                    Bitmap data1 = extras.getParcelable("data");
                    ivHeadicon.setImageBitmap(data1);
                }
                break;
        }

    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }
public static Information information;

    public static void setInformation(Information information) {
        PersonalXinActivity.information = information;
    }

    public interface Information{
    void setOnInformation();
}

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        Toast.makeText(PersonalXinActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(PersonalXinActivity.this,PersonalCenterActivity.class));
        information.setOnInformation();

        xinxi = getSharedPreferences("xinxi", MODE_PRIVATE);
        edit = xinxi.edit();
        edit.putBoolean("boolean",true);
        edit.commit();
        finish();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }

    private File createImageStoragePath() {
        if (hasSdcard()) {
            appDir = new File("/sdcard/testImage/");
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            date = new Date();
            str = simpleDateFormat.format(date);
            String fileName = str + ".jpg";
            File file = new File(appDir, fileName);
            return file;
        } else {
            Log.e("sd", "is not load");
            return null;
        }
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 3000);
    }

}
