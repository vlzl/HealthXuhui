package com.wondersgroup.healthxuhui.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 对SecondActivity列表项进行封装的实体类
 * Created by yangjinxi on 2016/6/20.
 */
public class SecondItemEntity implements Parcelable{
    /**
     *
     */
    private String itemName;
    /**
     *此项的名称
     */
    private String name;
    /**
     *此项所代表的url
     */
    private String url;


    protected SecondItemEntity(Parcel in) {
        itemName = in.readString();
        name = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(name);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SecondItemEntity> CREATOR = new Creator<SecondItemEntity>() {
        @Override
        public SecondItemEntity createFromParcel(Parcel in) {
            return new SecondItemEntity(in);
        }

        @Override
        public SecondItemEntity[] newArray(int size) {
            return new SecondItemEntity[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SecondItemEntity() {
    }

    public SecondItemEntity(String itemName, String name, String url) {
        this.itemName = itemName;
        this.name = name;
        this.url = url;
    }
}
