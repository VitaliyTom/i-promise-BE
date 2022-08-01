package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public UserDto create(@RequestBody UserDto userDto, HttpSession session) {
        return userService.addUser(userDto, session);
    }

    @PostMapping("/login")
    public UserDto logIn(@RequestBody UserDto userDto, HttpSession session) {
        return userService.logIn(userDto, session);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        userService.logOut(session);
        return ResponseEntity.status(HttpStatus.OK).body("BYE!!!").toString();
    }
}
