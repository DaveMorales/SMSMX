package com.minimalsoft.smsmx.Models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by David Morales on 18/12/17.
 */

public class MessageListResponse {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("totalDaily")
    @Expose
    private String totalDaily;

    @SerializedName("alertSent")
    @Expose
    private String alertSent;

    @SerializedName("diffusionSent")
    @Expose
    private String diffusionSent;

    @SerializedName("failed")
    @Expose
    private String failed;

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

    public String getTotalDaily() {
        return totalDaily;
    }

    public void setTotalDaily(String totalDaily) {
        this.totalDaily = totalDaily;
    }

    public String getAlertSent() {
        return alertSent;
    }

    public void setAlertSent(String alertSent) {
        this.alertSent = alertSent;
    }

    public String getDiffusionSent() {
        return diffusionSent;
    }

    public void setDiffusionSent(String diffusionSent) {
        this.diffusionSent = diffusionSent;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("number")
        @Expose
        private String number;

        @SerializedName("message")
        @Expose
        private String message;

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("timestamp")
        @Expose
        private String timestamp;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

    }

}