package com.sota.entity;

import javax.persistence.*;

/**
 * Created by wenjin on 2017/5/8.
 */
@Entity
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int userID1;
    private int userID2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID1() {
        return userID1;
    }

    public void setUserID1(int userID1) {
        this.userID1 = userID1;
    }

    public int getUserID2() {
        return userID2;
    }

    public void setUserID2(int userID2) {
        this.userID2 = userID2;
    }
}
