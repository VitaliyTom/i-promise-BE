package com.exadel.ipromise.converter.dto2Entity;

import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserEntityConverter {

    public User convert(UserDto userDto) {
        return new User(userDto.getUserId(), userDto.getNickname(), userDto.getEmail(), userDto.getPassword());
    }

}
