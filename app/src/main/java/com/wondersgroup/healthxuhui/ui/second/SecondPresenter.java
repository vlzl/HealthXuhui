package com.wondersgroup.healthxuhui.ui.second;

import com.wondersgroup.healthxuhui.core.AppApplication;
import com.wondersgroup.healthxuhui.data.entity.SecondItemEntity;
import com.wondersgroup.healthxuhui.util.AppUrlUtil;

import java.util.List;

/**
 * Created by yang on 16/7/3.
 */
public class SecondPresenter implements SecondContract.Presenter {
    private SecondContract.View mView;

    public SecondPresenter(SecondContract.View view){
        this.mView = view;
        mView.setPresenter(this);
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadItemList(SecondItemEnum secondItemEnum) {
        String itemName = "";
        switch (secondItemEnum){
            case QYWS:
                itemName = "qyws";
                break;
            case YLFW:
                itemName = "ylfw";
                break;
            case GGWS:
                itemName = "ggws";
                break;
        }
        List<SecondItemEntity> datas = AppUrlUtil.getSecondItemListByItemName(AppApplication.getInstance().getApplicationContext(), itemName);
        mView.showItemList(datas);
    }

    @Override
    public void openBrowser(String url) {
        mView.showBrowser(url);
    }
}
