package com.wondersgroup.healthxuhui.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by liyaqing on 2016/6/24.
 */
public class VersonUtil {
    //获取app versionCode
    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

}
