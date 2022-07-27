package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.dto2Entity.UserDtoToUserEntityConverter;
import com.exadel.ipromise.converter.entity2Dto.UserEntityToUserDtoConverter;
import com.exadel.ipromise.dao.UserDao;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import com.exadel.ipromise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDtoToUserEntityConverter userDtoToUserEntityConverter;

    @Autowired
    UserEntityToUserDtoConverter userEntityToUserDtoConverter;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public void addUser(UserDto userDto) {
        User user = userDtoToUserEntityConverter.convert(userDto);
        if (!userDao.checkUserByEmail(user)) {
            userDao.create(user);
        }
    }

    @Override
    @Transactional
    public UserDto getUserById(int id) {
        User user = userDao.getUserById(id);
        return userEntityToUserDtoConverter.convert(user);
    }

    @Override
    @Transactional
    public Boolean checkUserByEmail(UserDto userDto) {
        User user = userDtoToUserEntityConverter.convert(userDto);
        return userDao.checkUserByEmail(user);
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(UserDto userDto) {

        User user = userDtoToUserEntityConverter.convert(userDto);

        User userEntity = userDao.getUserByEmail(user);
        if (validUser(user, userEntity)) {
            return userEntityToUserDtoConverter.convert(userEntity);
        } else {
            return new UserDto();
        }
    }

    public Boolean validUser(User user, User userEntity) {
        return user.getEmail().equals(userEntity.getEmail()) && user.getPassword().equals(userEntity.getPassword());
    }
}
