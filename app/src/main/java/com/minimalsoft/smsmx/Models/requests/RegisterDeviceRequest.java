package com.minimalsoft.smsmx.Models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David Morales on 19/12/17.
 */

public class RegisterDeviceRequest {

    @SerializedName("deviceName")
    @Expose
    private String deviceName;

    @SerializedName("oneSignalid")
    @Expose
    private String oneSignalid;

    @SerializedName("oneSignalToken")
    @Expose
    private String oneSignalToken;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOneSignalid() {
        return oneSignalid;
    }

    public void setOneSignalid(String oneSignalid) {
        this.oneSignalid = oneSignalid;
    }

    public String getOneSignalToken() {
        return oneSignalToken;
    }

    public void setOneSignalToken(String oneSignalToken) {
        this.oneSignalToken = oneSignalToken;
    }


}
