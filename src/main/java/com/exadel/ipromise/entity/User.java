package com.exadel.ipromise.entity;

public class User {

    private int userId;
    private String nickname;
    private String email;
    private int promiseId;

    public User() {}

    public User(int userId, String nickname, String email, int promiseId) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.promiseId = promiseId;
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

    public int getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(int promiseId) {
        this.promiseId = promiseId;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", promiseId=" + promiseId +
                '}';
    }
}