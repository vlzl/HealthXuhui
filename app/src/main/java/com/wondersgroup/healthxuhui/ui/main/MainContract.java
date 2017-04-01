package com.wondersgroup.healthxuhui.ui.main;

import com.wondersgroup.healthxuhui.data.entity.VersonUpdateEntity;
import com.wondersgroup.healthxuhui.ui.base.BasePresenter;
import com.wondersgroup.healthxuhui.ui.base.BaseView;
import com.wondersgroup.healthxuhui.ui.second.SecondItemEnum;

/**
 * Created by yang on 16/7/3.
 */
public class MainContract {
    public interface Presenter extends BasePresenter {

        /**
         * 加载首页
         */
        void loadHomeWebPage();

        /**
         * 更新版本
         */
        void updateVerion();

        /**
         * 打开区域概况
         */
        void openQygk();

        /**
         *打开区域卫生
         */
        void openQyws();

        /**
         *打开医疗服务
         */
        void openYlfw();

        /**
         *打开公共卫生
         */
        void openGGws();

    }

    interface View extends BaseView<Presenter> {
        /**
         * 加载URL
         * @param url
         */
        void loadUrl(String url);

        /**
         * 显示地图
         */
        void showMap();

        /**
         * 进入二级菜单
         * @param secondType
         */
        void showSecond(SecondItemEnum secondType);

        /**
         * 浏览器activity 显示网页
         * @param url
         */
        void showBrowser(String url);

        /**
         * 显示更新对话框
         * @param entity
         */
        void showUpdateDialog(VersonUpdateEntity entity);
    }

}
