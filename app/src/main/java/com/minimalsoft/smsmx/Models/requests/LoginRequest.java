package com.minimalsoft.smsmx.Models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David Morales on 19/12/17.
 */

public class LoginRequest {

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
