package com.exadel.ipromise.converter;

import com.exadel.ipromise.dto.UserAuthDto;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.dto.UserUpdateDto;
import com.exadel.ipromise.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertToEntity(UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getNickName(), userDto.getEmail(), userDto.getPassword());
    }

    public User convertToEntity(UserUpdateDto userUpdateDto) {
        return new User(userUpdateDto.getUserId(), userUpdateDto.getNickName(), userUpdateDto.getEmail(), userUpdateDto.getPassword());
    }

    public User convertToEntity(UserAuthDto userAuthDto) {
        return new User(userAuthDto.getEmail(), userAuthDto.getPassword());
    }

    public UserDto convertToDto(User user) {
        return new UserDto(user.getUserId(), user.getNickName(), user.getEmail(), user.getPassword());
    }

}
