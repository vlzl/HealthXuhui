package com.wondersgroup.healthxuhui.ui.browser;

import com.wondersgroup.healthxuhui.util.LogUtil;

/**
 * 浏览器加载网页时的回调接口
 * Created by yang on 16/7/3.
 */
public abstract class BrowserCallback {
    /**
     *加载网页进度
     * @param progress 当前进度
     */
    public void inProgress(int progress){
    }

    /**
     *加载网页失败时
     */
    public void onError(){

    }

    /**
     * 接收到title时
     * @param title
     */
    public void onReceivedTitle(String title){
    }
}
