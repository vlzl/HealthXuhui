package com.wondersgroup.healthxuhui.ui.map;

import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;
import com.wondersgroup.healthxuhui.ui.base.BasePresenter;
import com.wondersgroup.healthxuhui.ui.base.BaseView;

import java.util.List;

/**
 * Created by yang on 16/7/5.
 */
public interface MapContract {
    interface Presenter extends BasePresenter{
        /**
         * 获取社区卫生服务中心列表数据
         */
        void getDataList();
    }

    interface View extends BaseView<Presenter>{
        void showDataList(List<HealthServiceCenterEntity> list);
        void showInfoWindow();
        void turnViewPager(int position);
    }
}
