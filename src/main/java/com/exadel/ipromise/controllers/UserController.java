package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/sign-up")
    public String signUp(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("user " + userDto.getNickName() + " added!").toString();
    }

    @GetMapping("/user/get-user-id")
    public UserDto getUserId(@RequestParam("id") int id, HttpSession session) {
        if (session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged")) {
            return (UserDto) session.getAttribute("user");

        } else {
            UserDto userDto = userService.getUserById(id);
            session.setAttribute("isLogged", true);
            session.setAttribute("user", userDto);
            return userDto;
        }
    }

    @RequestMapping(value = "/user/get-user-email")
    public UserDto getUserEmail(UserDto userDto, HttpSession session) {
        if ((Boolean) session.getAttribute("userExists")) {
            UserDto checkedUserDto = userService.getUserByEmail(userDto);
            if (checkedUserDto.getNickName() == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong login or password");
            }
            session.setAttribute("isLogged", true);
            session.setAttribute("user", checkedUserDto);
            return checkedUserDto;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong login or password");
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ModelAndView logIn(@RequestBody ModelMap model, HttpSession session) {
        UserDto userDto = new UserDto(0,
                (String) model.getAttribute("nickName"),
                (String) model.getAttribute("email"),
                (String) model.getAttribute("password"));

        session.setAttribute("userExists", userService.checkUserByEmail(userDto));
        return new ModelAndView("redirect:/user/get-user-email", model);
    }
}
