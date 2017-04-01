package com.wondersgroup.healthxuhui.ui.main;

import com.wondersgroup.healthxuhui.core.AppApplication;
import com.wondersgroup.healthxuhui.data.source.DataSource;
import com.wondersgroup.healthxuhui.data.entity.HttpResponeEntity;
import com.wondersgroup.healthxuhui.data.entity.SecondItemEntity;
import com.wondersgroup.healthxuhui.data.entity.VersonUpdateEntity;
import com.wondersgroup.healthxuhui.ui.permission.PermissionsActivity;
import com.wondersgroup.healthxuhui.ui.permission.PermissionsChecker;
import com.wondersgroup.healthxuhui.ui.second.SecondItemEnum;
import com.wondersgroup.healthxuhui.util.AppUrlUtil;
import com.wondersgroup.healthxuhui.util.LogUtil;
import com.wondersgroup.healthxuhui.util.VersonUtil;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yang on 16/7/3.
 */
public class MainPresenter implements MainContract.Presenter{

    private final CompositeSubscription mCompositeSubscription;
    private MainContract.View mainView;
    private DataSource mDataSource;

    public MainPresenter(DataSource dataSource, MainContract.View view){
        mainView = view;
        mainView.setPresenter(this);
        this.mDataSource = dataSource;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeSubscription.clear();
    }

    @Override
    public void loadHomeWebPage() {
        String homeUrl = AppUrlUtil.getHomeUrl(AppApplication.getInstance().getApplicationContext());
        mainView.loadUrl(homeUrl);
    }

    @Override
    public void updateVerion() {
        Subscription subscription = mDataSource.getLatestVersionInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResponeEntity<List<VersonUpdateEntity>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("version onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("version onError");
                    }

                    @Override
                    public void onNext(HttpResponeEntity<List<VersonUpdateEntity>> listHttpResponeEntity) {
                        LogUtil.i("version onNext");
                        int currVersion = VersonUtil.getVersionCode(AppApplication.getInstance().getApplicationContext());
                        VersonUpdateEntity entity = listHttpResponeEntity.getLocalObject().get(0);
                        if (currVersion < entity.getVersionId()){
                            mainView.showUpdateDialog(entity);
                        }else {
                            LogUtil.i("已经是最新版本了");
                        }
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void openQygk() {
        List<SecondItemEntity> qygk = AppUrlUtil.getSecondItemListByItemName(AppApplication.getInstance().getApplicationContext(), "qygk");
        mainView.showBrowser(qygk.get(0).getUrl());
    }

    @Override
    public void openQyws() {
        mainView.showSecond(SecondItemEnum.QYWS);
    }

    @Override
    public void openYlfw() {
        mainView.showSecond(SecondItemEnum.YLFW);
    }

    @Override
    public void openGGws() {
        mainView.showSecond(SecondItemEnum.GGWS);
    }

}
