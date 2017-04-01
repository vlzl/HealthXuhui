package com.wondersgroup.healthxuhui.data.source;

import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;
import com.wondersgroup.healthxuhui.data.entity.HttpResponeEntity;
import com.wondersgroup.healthxuhui.data.entity.VersonUpdateEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by yang on 16/7/4.
 */
public interface DataSource {
    /**
     * 获取最新的版本信息
     * @return
     */
    Observable<HttpResponeEntity<List<VersonUpdateEntity>>> getLatestVersionInfo();


    /**
     * 获取社区卫生服务中心列表信息
     * @return
     */
    Observable<HttpResponeEntity<List<HealthServiceCenterEntity>>> getHealthServiceCenterList();
}
