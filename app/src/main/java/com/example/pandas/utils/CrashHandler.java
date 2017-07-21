package com.example.pandas.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 系统默认UncaughExceptionHandler
     */
    private Thread.UncaughtExceptionHandler myHandler;

    private Context context;

    /**
     * 存储异常和参数信息
     */
    private Map<String, String> params = new HashMap<>();

    /**
     * 格式化时间
     */
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String TAG = this.getClass().getSimpleName();

    private static CrashHandler handler;

    private CrashHandler() {

    }

    /**
     * 获取实例
     */
    public static CrashHandler getInstance() {
        if (handler == null) {
            synchronized (CrashHandler.class) {
                if (handler == null) {
                    handler = new CrashHandler();
                }
            }
        }
        return handler;
    }

    public void init(Context context) {
        this.context = context;
        myHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler  为系统默认
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    /**
     * 回调函数
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && myHandler != null) {//如果自己没处理交给系统处理
            myHandler.uncaughtException(t, e);
        } else {//自己处理
            Process.killProcess(Process.myPid());
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
//        收集设备参数
        collectDeviceInfo(context);

//        使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(context, "程序开小差了", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
//        保存日志文件
        saveCrashInfoFile(ex);
        return true;
    }

    public void collectDeviceInfo(Context context) {
//        获取VersionName versionCode
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (info != null) {
                String versionName = info.versionName == null ? "null" : info.versionName;
                String versionCode = info.versionCode + "";
                params.put("versionName", versionName);
                params.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect crash info:" + e);
        }
//        获取所有系统信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                params.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {
                Log.e(TAG, "an error occured when collect crash info:" + e);
            }
        }
    }

    private String saveCrashInfoFile(Throwable e) {
        StringBuffer bu = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            bu.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        printWriter.close();
        String result = writer.toString();
        bu.append(result);
        try {
            long stamp = System.currentTimeMillis();
            String time = this.format.format(new Date());
            String fileName = "crash-" + time + "-" + stamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(bu.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}