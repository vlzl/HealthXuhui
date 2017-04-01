package com.wondersgroup.healthxuhui.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wondersgroup.healthxuhui.core.AppConstant;

/**
 * Created by yangjinxi on 2016/4/21.
 */
public class SPUtils {
    /**
     * 上一次弹出更新对话框日期的key
     */
    public static final String LAST_PROMPT_TIME = "last_prompt_time";

    /**
     * 获取SharedPreferences
     * @param context
     * @return
     */
    public static SharedPreferences getSP(Context context){
        SharedPreferences sp = context.getSharedPreferences(AppConstant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sp;
    }
}
