package com.exadel.ipromise.service;

import com.exadel.ipromise.dto.UserDto;

import javax.servlet.http.HttpSession;

public interface UserService {

    UserDto addUser(UserDto userDto, HttpSession session);

    UserDto update(UserDto userDto, HttpSession session);

    UserDto logIn(UserDto userDto, HttpSession session);

    void logOut(HttpSession session);

}
