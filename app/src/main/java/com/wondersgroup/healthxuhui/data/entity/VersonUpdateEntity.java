package com.wondersgroup.healthxuhui.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liyaqing on 2016/6/24.
 */
public class VersonUpdateEntity  implements Parcelable{


    /**
     * apkUrl : dsfsd
     * fbrq : 2016-10-01
     * isForce : 0
     * type : 1
     * versionChange : rer
     * versionId : 5
     * versionNumber : v2
     */

    private String apkUrl;
    private String fbrq;
    private int isForce;
    private int type;
    private String versionChange;
    private int versionId;
    private String versionNumber;

    protected VersonUpdateEntity(Parcel in) {
        apkUrl = in.readString();
        fbrq = in.readString();
        isForce = in.readInt();
        type = in.readInt();
        versionChange = in.readString();
        versionId = in.readInt();
        versionNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apkUrl);
        dest.writeString(fbrq);
        dest.writeInt(isForce);
        dest.writeInt(type);
        dest.writeString(versionChange);
        dest.writeInt(versionId);
        dest.writeString(versionNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VersonUpdateEntity> CREATOR = new Creator<VersonUpdateEntity>() {
        @Override
        public VersonUpdateEntity createFromParcel(Parcel in) {
            return new VersonUpdateEntity(in);
        }

        @Override
        public VersonUpdateEntity[] newArray(int size) {
            return new VersonUpdateEntity[size];
        }
    };

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getFbrq() {
        return fbrq;
    }

    public void setFbrq(String fbrq) {
        this.fbrq = fbrq;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersionChange() {
        return versionChange;
    }

    public void setVersionChange(String versionChange) {
        this.versionChange = versionChange;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }
}
