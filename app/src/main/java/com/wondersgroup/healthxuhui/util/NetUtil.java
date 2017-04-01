package com.wondersgroup.healthxuhui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;

/**
 * Created by yang on 16/7/10.
 */
public class NetUtil {

    public static final int MOBILE = 0;//移动网络
    public static final int WIFI = 1;//WiFi网络
    public static final int EMPTY =2;//都不是

    /**
     * 获取当前网络类型
     * @param context
     * @return
     */
    public static int getNetType(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        int type = networkInfo.getType();
        switch (type){
            case ConnectivityManager.TYPE_WIFI:
                LogUtil.i("wifi");
                return WIFI;
            case ConnectivityManager.TYPE_MOBILE:
                LogUtil.i("mobile");
                return MOBILE;
            default:
                return EMPTY;
        }
    }
}
