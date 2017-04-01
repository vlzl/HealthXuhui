package com.wondersgroup.healthxuhui.http;

import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;
import com.wondersgroup.healthxuhui.data.entity.HttpResponeEntity;
import com.wondersgroup.healthxuhui.data.entity.VersonUpdateEntity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yang on 16/7/5.
 */
public interface ApiStores {
    /**
     * app service url
     */
    String BASE_URL = "http://180.166.42.108:7001/xhws_webapp/";

    /**
     * 获取最新的版本信息
     * @return
     */
    @GET("update.action?type=android")
    Observable<HttpResponeEntity<List<VersonUpdateEntity>>> getLatestVerion();


    /**
     * 获取社区卫生服务中心列表信息
     * @return
     */
    @GET("jwd.action")
    Observable<HttpResponeEntity<List<HealthServiceCenterEntity>>> getHealthServiceCenterList();
}

