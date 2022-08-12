package com.exadel.ipromise.converter;

import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertToEntity(UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getNickName(), userDto.getEmail(), userDto.getPassword());
    }

    public UserDto convertToDto(User user) {
        return new UserDto(user.getUserId(), user.getNickName(), user.getEmail(), user.getPassword());
    }

}
