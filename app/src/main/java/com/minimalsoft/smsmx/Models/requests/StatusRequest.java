package com.minimalsoft.smsmx.Models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David Morales on 18/12/17.
 */

public class StatusRequest {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("status")
    @Expose
    private String status;

    public StatusRequest(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
