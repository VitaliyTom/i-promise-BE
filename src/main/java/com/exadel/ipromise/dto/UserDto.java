package com.exadel.ipromise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private int userId;
    private String nickName;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public UserDto() {
    }

    public UserDto(int userId, String nickName, String email, String password) {
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
