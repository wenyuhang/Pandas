package com.example.pandas.personal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;

import java.io.File;
import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.OnClick;

import static com.example.pandas.R.id.personal_set_fankui_layout;

public class SetActivity extends BaseActivity {

    @Bind(R.id.fanhui)
    ImageButton fanhui;
    @Bind(R.id.pe_set_push_view1)
    ImageView peSetPushView1;
    @Bind(R.id.pe_set_push_view2)
    ImageView peSetPushView2;
    @Bind(R.id.pe_set_play_view1)
    ImageView peSetPlayView1;
    @Bind(R.id.pe_set_play_view2)
    ImageView peSetPlayView2;
    @Bind(R.id.set_cache_size_tv)
    TextView setCacheSizeTv;
    @Bind(R.id.personal_set_delete_cache_layout)
    RelativeLayout personalSetDeleteCacheLayout;
    @Bind(personal_set_fankui_layout)
    RelativeLayout personalSetFankuiLayout;
    @Bind(R.id.personal_set_udpate_layout)
    RelativeLayout personalSetUdpateLayout;
    @Bind(R.id.personal_set_ping_layout)
    RelativeLayout personalSetPingLayout;
    @Bind(R.id.personal_set_about_layout)
    RelativeLayout personalSetAboutLayout;
    @Bind(R.id.activity_set)
    LinearLayout activitySet;
    private static long cacheSize;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initview() {
        try {
            getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double d = (double)cacheSize/1024/1024;

        if(d>=1.00) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
            String format = df.format(d);
            setCacheSizeTv.setText(format+"MB");
        }else {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
            String format = df.format(d);
            setCacheSizeTv.setText("0"+format+"MB");
        }
    }

    @OnClick({R.id.fanhui, R.id.pe_set_push_view1, R.id.pe_set_push_view2, R.id.pe_set_play_view1, R.id.pe_set_play_view2, R.id.personal_set_delete_cache_layout, personal_set_fankui_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.pe_set_push_view1:
                peSetPushView1.setVisibility(View.GONE);
                peSetPushView2.setVisibility(View.VISIBLE);
                break;
            case R.id.pe_set_push_view2:
                peSetPushView2.setVisibility(View.GONE);
                peSetPushView1.setVisibility(View.VISIBLE);
                break;
            case R.id.pe_set_play_view1:
                peSetPlayView1.setVisibility(View.GONE);
                peSetPlayView2.setVisibility(View.VISIBLE);
                break;
            case R.id.pe_set_play_view2:
                peSetPlayView2.setVisibility(View.GONE);
                peSetPlayView1.setVisibility(View.VISIBLE);
                break;
            case R.id.personal_set_delete_cache_layout:

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("清除缓存");
                dialog.show();
                dialog.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAllCache(SetActivity.this);
                        setCacheSizeTv.setText("0.00MB");
                        Toast.makeText(SetActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                break;
            case personal_set_fankui_layout:

                startActivity(new Intent(SetActivity.this,UserFeedbackActivity.class));

                break;
            case R.id.personal_set_udpate_layout:
                Toast.makeText(SetActivity.this, "更新什么啊，是不是流量多了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_set_ping_layout:
                break;
            case R.id.personal_set_about_layout:
                    startActivity(new Intent(SetActivity.this,AboutActivity.class));
                break;
        }
    }
    /**
     * 获取缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
            Log.e("TAG", cacheSize +"");
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除缓存
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    // 获取文件大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
//    @OnClick({R.id.fanhui, pe_set_push_view1, R.id.pe_set_push_view2, R.id.pe_set_play_view1, R.id.pe_set_play_view2, R.id.set_cache_size_tv, R.id.personal_set_delete_cache_layout, R.id.personal_set_fankui_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.fanhui:
//                finish();
//                break;
//            case pe_set_push_view1:
//                Toast.makeText(SetActivity.this, "66", Toast.LENGTH_SHORT).show();
//                peSetPushView1.setVisibility(View.VISIBLE);
//                peSetPushView2.setVisibility(View.GONE);
//                break;
//            case R.id.pe_set_push_view2:
//                peSetPushView1.setVisibility(View.GONE);
//                peSetPushView2.setVisibility(View.VISIBLE);
//                break;
//            case R.id.pe_set_play_view1:
//                peSetPushView1.setVisibility(View.VISIBLE);
//                peSetPushView2.setVisibility(View.GONE);
//                break;
//            case R.id.pe_set_play_view2:
//                peSetPushView1.setVisibility(View.GONE);
//                peSetPushView2.setVisibility(View.VISIBLE);
//                break;
//            case R.id.set_cache_size_tv:
//                break;
//            case R.id.personal_set_delete_cache_layout:
//                break;
//            case R.id.personal_set_fankui_layout:
//                break;
//            case R.id.personal_set_udpate_layout:
//                break;
//            case R.id.personal_set_ping_layout:
//                break;
//            case R.id.personal_set_about_layout:
//                break;
//        }
//    }
}
