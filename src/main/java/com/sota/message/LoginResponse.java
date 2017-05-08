package com.sota.message;

import com.sota.entity.User;
import com.sota.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wenjin on 2017/5/8.
 */
public class LoginResponse {
    private String loginResult;
    private int userID;
    //friend message
    private FriendMesaage[] friendMesaages;
    private ImageMessage[] imageMessages;


    public LoginResponse(String loginResult, int userID, FriendMesaage[] friendMesaages, ImageMessage[] imageMessages) {
        this.loginResult = loginResult;
        this.userID = userID;
        this.friendMesaages = friendMesaages;
        this.imageMessages = imageMessages;
    }

    public LoginResponse() {

    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }
    public String getLoginResult() {
        return loginResult;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public FriendMesaage[] getFriendMesaages() {
        return friendMesaages;
    }

    public void setFriendMesaages(FriendMesaage[] friendMesaages) {
        this.friendMesaages = friendMesaages;
    }

    public ImageMessage[] getImageMessages() {
        return imageMessages;
    }

    public void setImageMessages(ImageMessage[] imageMessages) {
        this.imageMessages = imageMessages;
    }
}
