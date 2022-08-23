package com.exadel.ipromise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class UserUpdateDto {

    @NotNull(message = "cannot be empty. ")
    private Long userId;

    @NotNull(message = "cannot be empty. ")
    @Size(min = 3, max = 50, message = "must be at least 3 characters and maximum 50. ")
    private String nickName;

    @NotNull(message = "cannot be empty. ")
    @Pattern(regexp = "([a-zA-Z]{3,})@[a-zA-Z]{3,}[.][a-zA-Z]{2,}", message = "must be a minimum of 10 characters and a maximum of 50. ")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "cannot be empty. ")
    @Size(min = 5, max = 50, message = "must be at least 5 characters and maximum 50. ")
    private String password;

    public UserUpdateDto() {
    }

    public UserUpdateDto(Long userId, String nickName, String email, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
