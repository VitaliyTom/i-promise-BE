package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public User() {
    }

    public User(int userId, String nickName, String email, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}