package com.wondersgroup.healthxuhui.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 卫生服务中心实体类
 * Created by yangjinxi on 2016/6/22.
 */
public class HealthServiceCenterEntity implements Parcelable {

    /**
     * name : 徐汇区斜土街道社区卫生服务中心
     * address : 上海市徐汇区零陵北路2号
     * code : 200032
     * jingdu : 121.47112
     * weidu : 31.20274
     * telephone : 64042727
     * qxrk : 3000
     * yjyqy : 4696
     * type : 2
     * show : 1
     */

    private String name;//名称
    private String address;//地址
    private String code;//邮编
    private double jingdu;//经度
    private double weidu;//纬度
    private String telephone;//电话
    private int qxrk;//区县人口
    private int yjyqy;//1+1签约
    private int type;//机构类型:0代表市疾控；1代表区疾控；2代表社区；3代表专业站所；4代表二级医院；5代表三级医院
    private int show;//是否显示:0代表不显示；1代表显示(区县人口,壹加壹签约人数)

    protected HealthServiceCenterEntity(Parcel in) {
        name = in.readString();
        address = in.readString();
        code = in.readString();
        jingdu = in.readDouble();
        weidu = in.readDouble();
        telephone = in.readString();
        qxrk = in.readInt();
        yjyqy = in.readInt();
        type = in.readInt();
        show = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(code);
        dest.writeDouble(jingdu);
        dest.writeDouble(weidu);
        dest.writeString(telephone);
        dest.writeInt(qxrk);
        dest.writeInt(yjyqy);
        dest.writeInt(type);
        dest.writeInt(show);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HealthServiceCenterEntity> CREATOR = new Creator<HealthServiceCenterEntity>() {
        @Override
        public HealthServiceCenterEntity createFromParcel(Parcel in) {
            return new HealthServiceCenterEntity(in);
        }

        @Override
        public HealthServiceCenterEntity[] newArray(int size) {
            return new HealthServiceCenterEntity[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getQxrk() {
        return qxrk;
    }

    public void setQxrk(int qxrk) {
        this.qxrk = qxrk;
    }

    public int getYjyqy() {
        return yjyqy;
    }

    public void setYjyqy(int yjyqy) {
        this.yjyqy = yjyqy;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public HealthServiceCenterEntity() {
    }
}
