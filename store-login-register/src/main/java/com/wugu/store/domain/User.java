package com.wugu.store.domain;

import org.springframework.context.annotation.Bean;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String userName;
    private String passWord;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
