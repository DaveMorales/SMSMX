package com.minimalsoft.smsmx.Models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David Morales on 18/12/17.
 */

public class StatusResponse {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("message")
    @Expose
    private String message;

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
}
