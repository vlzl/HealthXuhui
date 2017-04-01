package com.wondersgroup.healthxuhui.ui.second;

import com.wondersgroup.healthxuhui.data.entity.SecondItemEntity;
import com.wondersgroup.healthxuhui.ui.base.BasePresenter;
import com.wondersgroup.healthxuhui.ui.base.BaseView;

import java.util.List;

/**
 * Created by yang on 16/7/3.
 */
public interface SecondContract {
    interface Presenter extends BasePresenter{
        /**
         * 加载item列表
         */
        void loadItemList(SecondItemEnum secondItemEnum);

        /**
         * 打开URL页面
         * @param url
         */
        void openBrowser(String url);

    }

    interface View extends BaseView<Presenter>{
        /**
         * 显示item列表
         */
        void showItemList(List<SecondItemEntity> list);

        /**
         * 显示URL页面
         * @param url
         */
        void showBrowser(String url);
    }
}
