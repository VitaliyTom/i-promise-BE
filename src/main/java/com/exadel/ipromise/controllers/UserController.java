package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/signUp", method = RequestMethod.POST)
    public String signUp(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return "user " + userDto.getNickname() + " added!";
    }

    @RequestMapping(value = "/user/getUserId", method = RequestMethod.GET)
    public String getUserId(@RequestParam("id") int id, HttpSession session) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            if (session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged")) {
                UserDto userDto = (UserDto) session.getAttribute("user");
                return objectMapper.writeValueAsString(userDto);

            } else {
                UserDto userDto = userService.getUserById(id);
                session.setAttribute("isLogged", true);
                session.setAttribute("user", userDto);
                return objectMapper.writeValueAsString(userDto);
            }
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/user/getUserEmail")
    public String getUserEmail(UserDto userDto, HttpSession session) throws JsonProcessingException {
        try {
            if ((Boolean) session.getAttribute("userExists")) {
                ObjectMapper objectMapper = new ObjectMapper();
                UserDto checkedUserDto = userService.getUserByEmail(userDto);
                String mapper = objectMapper.writeValueAsString(checkedUserDto);
                if (checkedUserDto.getNickname() == null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong login or password\n").toString();
                }
                session.setAttribute("isLogged", true);
                session.setAttribute("user", checkedUserDto);
                return mapper;
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong login or password\n").toString();
            }
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/user/logIn", method = RequestMethod.POST)
    public ModelAndView logIn(@RequestBody ModelMap model, HttpSession session) {
        UserDto userDto = new UserDto(0,
                (String) model.getAttribute("nickname"),
                (String) model.getAttribute("email"),
                (String) model.getAttribute("password"));

        session.setAttribute("userExists", userService.checkUserByEmail(userDto));
        return new ModelAndView("redirect:/user/getUserEmail", model);
    }
}
