package com.sota.message;

/**
 * Created by wenjin on 2017/5/8.
 */
public class LoginResponse {
    private boolean loginResult;
    private int userID;
    private FriendMessage[] friendMessages;
    private ImageMessage[] imageMessages;


    public LoginResponse(boolean loginResult, int userID, FriendMessage[] friendMessages, ImageMessage[] imageMessages) {
        this.loginResult = loginResult;
        this.userID = userID;
        this.friendMessages = friendMessages;
        this.imageMessages = imageMessages;
    }

    public LoginResponse() {

    }

    public void setLoginResult(boolean loginResult) {
        this.loginResult = loginResult;
    }
    public boolean getLoginResult() {
        return loginResult;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public FriendMessage[] getFriendMessages() {
        return friendMessages;
    }

    public void setFriendMessages(FriendMessage[] friendMessages) {
        this.friendMessages = friendMessages;
    }

    public ImageMessage[] getImageMessages() {
        return imageMessages;
    }

    public void setImageMessages(ImageMessage[] imageMessages) {
        this.imageMessages = imageMessages;
    }
}
