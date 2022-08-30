package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.UserAuthDto;
import com.exadel.ipromise.dto.UserDto;
import com.exadel.ipromise.dto.UserUpdateDto;
import com.exadel.ipromise.exception.FieldValidationAuthException;
import com.exadel.ipromise.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto, BindingResult result, HttpSession session) {

        validation(result);
        return ResponseEntity.ok().body(userService.addUser(userDto, session));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult result, HttpSession session) {

        validation(result);
        return ResponseEntity.ok().body(userService.update(userUpdateDto, session));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> logIn(@Valid @RequestBody UserAuthDto userAuthDto, BindingResult result, HttpSession session) {

        validation(result);
        return ResponseEntity.ok().body(userService.logIn(userAuthDto, session));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        userService.logOut(session);
        return ResponseEntity.ok("Bye!!!");
    }

    private void validation(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> allErrors = result.getFieldErrors();
            StringBuilder errors = new StringBuilder();

            for (FieldError field : allErrors) {
                errors.append(field.getField()).append(": ").append(field.getDefaultMessage());
            }
            throw new FieldValidationAuthException(String.valueOf(errors));
        }
    }
}
