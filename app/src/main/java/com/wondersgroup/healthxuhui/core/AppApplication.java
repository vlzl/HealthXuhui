package com.wondersgroup.healthxuhui.core;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.webkit.WebView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.wondersgroup.healthxuhui.BuildConfig;
import com.wondersgroup.healthxuhui.ui.exception.ExceptionActivity;

/**
 * Created by yangjinxi on 2016/6/7.
 */
public class AppApplication extends Application {
    private static final String SYNC_LOCK = "SYNC_LOCK";
    private static AppApplication instance = null;

    public AppApplication(){}
    
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        if (BuildConfig.DEBUG){//在测试情况下捕捉闪退信息
////        if (true){//捕捉闪退信息
//            Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
//        }
    }

    public static AppApplication getInstance() {
        if (instance == null){
            synchronized (SYNC_LOCK){
                if (instance == null){
                    instance = new AppApplication();
                }
            }
        }
        return instance;
    }


    /**
     * 闪退捕获，并输出到ExceptionActivity中显示
     */
    class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            String exception = "";
            exception = ex.getMessage() + "\n";
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (StackTraceElement e : stackTrace) {
                exception += e.toString() + "\n";
            }
            Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AppApplication.this, ExceptionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(ExceptionActivity.EXCEPTION, exception);
            AppApplication.this.startActivity(intent);
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }
}
