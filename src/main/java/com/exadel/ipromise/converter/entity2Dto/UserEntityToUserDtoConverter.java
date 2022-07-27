package com.exadel.ipromise.converter.entity2Dto;

import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDtoConverter {

    public UserDto convert(User user) {
        return new UserDto(user.getUserId(), user.getNickname(), user.getEmail(), user.getPassword());
    }

}
