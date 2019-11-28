package com.wugu.store.domain;

import java.util.UUID;

public class Message {
    private UUID userID;
    private UUID phoneID;
    private int state;

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(UUID phoneID) {
        this.phoneID = phoneID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
