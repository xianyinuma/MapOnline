package com.sota.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wenjin on 2017/5/9.
 */
public class EditResponse {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    @SerializedName("uploadResponse")
    private UploadResponse uploadResponse;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UploadResponse getUploadResponse() {
        return uploadResponse;
    }

    public void setUploadResponse(UploadResponse uploadResponse) {
        this.uploadResponse = uploadResponse;
    }
}
