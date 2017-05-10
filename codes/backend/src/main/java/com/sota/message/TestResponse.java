package com.sota.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wenjin on 2017/5/9.
 */
public class TestResponse {
    @SerializedName("testName")
    private String testName;
    @SerializedName("testPassword")
    private String testPassword;
}
