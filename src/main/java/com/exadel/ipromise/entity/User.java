package com.exadel.ipromise.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public User() {
    }

    public User(int userId, String nickname, String email, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    @Override
    public String toString() {
        return "\nUser{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}