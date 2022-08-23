package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.UserConverter;
import com.exadel.ipromise.dao.UserDao;
import com.exadel.ipromise.dto.*;
import com.exadel.ipromise.entity.User;
import com.exadel.ipromise.exception.NoSuchUserExistsException;
import com.exadel.ipromise.exception.UserAlreadyExistsException;
import com.exadel.ipromise.exception.UserIsInvalidAuthException;
import com.exadel.ipromise.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final String USER_ALREADY_EXISTS = "User with this e-mail already exists";
    private final String NO_SUCH_USER_EXISTS = "no such user exists";
    private final String WRONG_LOGIN_OR_PASSWORD = "Wrong login or password!";

    private final UserConverter userConverter;
    private final UserDao userDao;

    public UserServiceImpl(UserConverter userConverter, UserDao userDao) {
        this.userConverter = userConverter;
        this.userDao = userDao;
    }

    @Override
    public UserDto addUser(UserDto userDto, HttpSession session) {

        User user = userConverter.convertToEntity(userDto);

        if (userDao.checkIfUserExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        }

        Long userID = userDao.create(user);
        UserDto newUserDto = userConverter.convertToDto(userDao.getById(userID));

        session.setAttribute("isLogged", true);
        session.setAttribute("user", newUserDto);

        return newUserDto;
    }

    @Override
    public UserDto update(UserUpdateDto userUpdateDto, HttpSession session) {

        User user = userConverter.convertToEntity(userUpdateDto);
        try {
            User updatedUser = userDao.update(user);
            UserDto newUserDto = userConverter.convertToDto(updatedUser);
            session.setAttribute("isLogged", true);
            session.setAttribute("user", newUserDto);
            return newUserDto;
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchUserExistsException(NO_SUCH_USER_EXISTS);
        }
    }

    @Override
    public UserDto logIn(UserAuthDto userAuthDto, HttpSession session) {

        User user = userConverter.convertToEntity(userAuthDto);

        try {

            User userData = userDao.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            UserDto newUserDto = userConverter.convertToDto(userData);

            session.setAttribute("isLogged", true);
            session.setAttribute("user", newUserDto);

            return newUserDto;

        } catch (EmptyResultDataAccessException e) {
            throw new UserIsInvalidAuthException(WRONG_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void logOut(HttpSession session) {
        session.setAttribute("isLogged", false);
        session.removeAttribute("user");
    }
}
