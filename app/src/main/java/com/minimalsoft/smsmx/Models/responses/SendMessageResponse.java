package com.minimalsoft.smsmx.Models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by David Morales on 18/12/17.
 */

public class SendMessageResponse {

    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;

    @SerializedName("type")
    @Expose
    private String type;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public class Message {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("number")
        @Expose
        private String number;

        @SerializedName("message")
        @Expose
        private String message;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
