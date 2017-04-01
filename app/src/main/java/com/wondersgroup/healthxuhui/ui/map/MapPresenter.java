package com.wondersgroup.healthxuhui.ui.map;

import com.wondersgroup.healthxuhui.data.source.DataSource;
import com.wondersgroup.healthxuhui.data.entity.HealthServiceCenterEntity;
import com.wondersgroup.healthxuhui.data.entity.HttpResponeEntity;
import com.wondersgroup.healthxuhui.util.LogUtil;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yang on 16/7/5.
 */
public class MapPresenter implements MapContract.Presenter {
    private MapContract.View mView;
    private DataSource mDataSource;
    private CompositeSubscription mCompositeSubscription;

    public MapPresenter(DataSource dataSource, MapContract.View view){
        mView = view;
        mView.setPresenter(this);
        mDataSource = dataSource;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void getDataList() {
        Subscription subscription = mDataSource.getHealthServiceCenterList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResponeEntity<List<HealthServiceCenterEntity>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("healthservicecenter onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("healthservicecenter onError");
                    }

                    @Override
                    public void onNext(HttpResponeEntity<List<HealthServiceCenterEntity>> listHttpResponeEntity) {
                        LogUtil.i("healthservicecenter onNext");
                        mView.showDataList(listHttpResponeEntity.getLocalObject());
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }
}
