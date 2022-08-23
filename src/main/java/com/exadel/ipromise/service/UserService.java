package com.exadel.ipromise.service;

import com.exadel.ipromise.dto.*;

import javax.servlet.http.HttpSession;

public interface UserService {

    UserDto addUser(UserDto userDto, HttpSession session);

    UserDto update(UserUpdateDto userUpdateDto, HttpSession session);

    UserDto logIn(UserAuthDto userAuthDto, HttpSession session);

    void logOut(HttpSession session);

}
