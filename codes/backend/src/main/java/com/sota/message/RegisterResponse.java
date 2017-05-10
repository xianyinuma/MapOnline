package com.sota.message;

/**
 * Created by wenjin on 2017/5/9.
 */
public class RegisterResponse {
    private int userID;
    private boolean registerResult;

    public RegisterResponse() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(boolean registerResult) {
        this.registerResult = registerResult;
    }
}
