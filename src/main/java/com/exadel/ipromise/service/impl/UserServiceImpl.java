package com.exadel.ipromise.service.impl;

import com.exadel.ipromise.converter.UserConverter;
import com.exadel.ipromise.dao.UserDao;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.entity.User;
import com.exadel.ipromise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final String USER_ALREADY_EXISTS = "User with this e-mail already exists";
    private final String WRONG_LOGIN_OR_PASSWORD = "Wrong login or password!";
    private final String SOMETHING_WENT_WRONG = "Something went wrong...";

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
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, USER_ALREADY_EXISTS);
        }

        Long userID = userDao.create(user);
        UserDto newUserDto = userConverter.convertToDto(userDao.getById(userID));

        session.setAttribute("isLogged", true);
        session.setAttribute("user", newUserDto);

        return newUserDto;
    }

    @Override
    public UserDto update(UserDto userDto, HttpSession session) {

        User user = userConverter.convertToEntity(userDto);
        try{
            User updatedUser = userDao.update(user);
            UserDto newUserDto = userConverter.convertToDto(updatedUser);
            session.setAttribute("isLogged", true);
            session.setAttribute("user", newUserDto);
            return newUserDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public UserDto logIn(UserDto userDto, HttpSession session) {

        User user = userConverter.convertToEntity(userDto);

        try {

            User userData = userDao.getUserByEmailAndPassword(user.getEmail(), userDto.getPassword());
            UserDto newUserDto = userConverter.convertToDto(userData);

            session.setAttribute("isLogged", true);
            session.setAttribute("user", newUserDto);

            return newUserDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, WRONG_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void logOut(HttpSession session) {
        session.setAttribute("isLogged", false);
        session.removeAttribute("user");
    }
}
