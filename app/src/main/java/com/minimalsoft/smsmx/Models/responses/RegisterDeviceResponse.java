package com.minimalsoft.smsmx.Models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by David Morales on 19/12/17.
 */

public class RegisterDeviceResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("idDevice")
        @Expose
        private int idDevice;

        public int getIdDevice() {
            return idDevice;
        }

        public void setIdDevice(int idDevice) {
            this.idDevice = idDevice;
        }

    }
    
}
