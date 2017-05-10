package com.sota.message;

/**
 * Created by wenjin on 2017/5/9.
 */
public class FriendImage {
    private int userID;
    private String username;
    private ImageMessage[] imageMessages;

    public FriendImage() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ImageMessage[] getImageMessages() {
        return imageMessages;
    }

    public void setImageMessages(ImageMessage[] imageMessages) {
        this.imageMessages = imageMessages;
    }
}
