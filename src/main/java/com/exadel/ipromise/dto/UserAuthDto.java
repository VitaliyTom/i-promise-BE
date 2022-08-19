package com.exadel.ipromise.dto;

import javax.validation.constraints.*;

public class UserAuthDto {

    @NotNull(message = "cannot be empty. ")
    @Pattern(regexp = "([a-zA-Z]{3,})@[a-zA-Z]{3,}[.][a-zA-Z]{2,}", message = "must be a minimum of 10 characters and a maximum of 50. ")
    private String email;

    @NotNull(message = "cannot be empty. ")
    @Size(min = 5, max = 50, message = "must be at least 5 characters and maximum 50. ")
    private String password;

    public UserAuthDto() {
    }

    public UserAuthDto(String email, String password) {
        this.email = email;
        this.password = password;
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
