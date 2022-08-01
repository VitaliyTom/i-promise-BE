package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.UserConverter;
import com.exadel.ipromise.dao.UserDao;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import com.exadel.ipromise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Service("UserService")
public class UserServiceImpl implements UserService {

    String USER_ALREADY_EXISTS = "User with this e-mail already exists";
    String USER_NOT_EXIST = "User with this e-mail does not exist";
    String WRONG_LOGIN_OR_PASSWORD = "Wrong login or password!";

    private final UserConverter userConverter;
    private final UserDao userDao;

    public UserServiceImpl(UserConverter userConverter, UserDao userDao) {
        this.userConverter = userConverter;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto, HttpSession session) {

        User user = userConverter.convertToEntity(userDto);

        if (userDao.checkIfUserExistsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, USER_ALREADY_EXISTS);
        }

        Integer userID = userDao.create(user);
        UserDto newUserDto = userConverter.convertToDto(userDao.getById(userID));

        session.setAttribute("isLogged", true);
        session.setAttribute("user", newUserDto);

        return newUserDto;
    }

    @Override
    public UserDto logIn(UserDto userDto, HttpSession session) {

        User user = userConverter.convertToEntity(userDto);

        if (!userDao.checkIfUserExistsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, USER_NOT_EXIST);
        }

        User userEntity = userDao.getByEmail(user.getEmail());

        if (validationUser(user, userEntity)) {

            UserDto newUserDto = userConverter.convertToDto(userEntity);
            session.setAttribute("isLogged", true);
            session.setAttribute("user", newUserDto);
            return newUserDto;

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, WRONG_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void logOut(HttpSession session) {
        session.setAttribute("isLogged", false);
        session.removeAttribute("user");
    }

    public Boolean validationUser(User user, User userEntity) {
        return user.getEmail().equals(userEntity.getEmail()) && user.getPassword().equals(userEntity.getPassword());
    }
}
