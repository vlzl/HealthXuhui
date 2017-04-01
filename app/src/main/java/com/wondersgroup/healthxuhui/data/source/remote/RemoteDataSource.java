package com.wondersgroup.healthxuhui.data.source.remote;

import com.wondersgroup.healthxuhui.data.source.DataSource;
import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;
import com.wondersgroup.healthxuhui.data.entity.HttpResponeEntity;
import com.wondersgroup.healthxuhui.data.entity.VersonUpdateEntity;
import com.wondersgroup.healthxuhui.http.ApiStores;
import com.wondersgroup.healthxuhui.http.AppClient;

import java.util.List;

import rx.Observable;

/**
 * Created by yang on 16/7/4.
 */
public class RemoteDataSource implements DataSource {
    private final ApiStores apiStores;
    private static RemoteDataSource mInstance;

    private RemoteDataSource(){
        apiStores = AppClient.getInstance().retrofit().create(ApiStores.class);
    }

    public static RemoteDataSource getInstance() {
        if (mInstance == null){
            mInstance = new RemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public Observable<HttpResponeEntity<List<VersonUpdateEntity>>> getLatestVersionInfo() {
        return apiStores.getLatestVerion();
    }

    @Override
    public Observable<HttpResponeEntity<List<HealthServiceCenterEntity>>> getHealthServiceCenterList() {
        return apiStores.getHealthServiceCenterList();
    }
}
