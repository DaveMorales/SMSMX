package com.minimalsoft.smsmx.Models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by David Morales on 19/12/17.
 */

public class LoginResponse {

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

        @SerializedName("idUser")
        @Expose
        private int idUser;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("isAdmin")
        @Expose
        private int isAdmin;

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }

    }
}
