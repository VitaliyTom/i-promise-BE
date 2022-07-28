package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.UserConverter;
import com.exadel.ipromise.dao.UserDao;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import com.exadel.ipromise.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserDao userDao;

    public UserServiceImpl(UserConverter userConverter, UserDao userDao) {
        this.userConverter = userConverter;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(UserDto userDto) {
        User user = userConverter.convertToDto(userDto);
        if (!userDao.checkIfUserExistsByEmail(user.getEmail())) {
            userDao.create(user);
        }
    }

    @Override
    @Transactional
    public UserDto getUserById(int id) {
        User user = userDao.getById(id);
        return userConverter.convertToEntity(user);
    }

    @Override
    @Transactional
    public Boolean checkUserByEmail(UserDto userDto) {
        User user = userConverter.convertToDto(userDto);
        return userDao.checkIfUserExistsByEmail(user.getEmail());
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(UserDto userDto) {

        User user = userConverter.convertToDto(userDto);
        User userEntity = userDao.getByEmail(user.getEmail());

        if (validUser(user, userEntity)) {
            return userConverter.convertToEntity(userEntity);
        } else {
            return new UserDto();
        }
    }

    public Boolean validUser(User user, User userEntity) {
        return user.getEmail().equals(userEntity.getEmail()) && user.getPassword().equals(userEntity.getPassword());
    }
}
