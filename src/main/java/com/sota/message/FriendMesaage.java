package com.sota.message;

/**
 * Created by wenjin on 2017/5/8.
 */
public class FriendMesaage {
    private int userID;
    private String name;

    public FriendMesaage() {
    }

    public FriendMesaage(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
