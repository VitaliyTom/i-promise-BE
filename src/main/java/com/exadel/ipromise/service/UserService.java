package com.exadel.ipromise.service;

import com.exadel.ipromise.dto.UserDto;

public interface UserService {

    void addUser(UserDto userDto);

    UserDto getUserById(int id);

    Boolean checkUserByEmail(UserDto userDto);

    UserDto getUserByEmail(UserDto userDto);

}
