package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.PromiseDto;
import com.exadel.ipromise.dto.PromiseListDto;
import com.exadel.ipromise.dto.PromiseUpdateDto;
import com.exadel.ipromise.exception.FieldValidationPromiseException;
import com.exadel.ipromise.service.PromiseService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/promise")
public class PromiseController {

    private final PromiseService promiseService;

    public PromiseController(PromiseService promiseService) {
        this.promiseService = promiseService;
    }

    @PostMapping
    public List<PromiseListDto> create(@Valid @RequestBody PromiseDto promiseDto, BindingResult result) {

        validation(result);
        return promiseService.addPromise(promiseDto);
    }

    @GetMapping
    public List<PromiseListDto> getAllPromises(@RequestParam(value = "user-id") Long userId) {
        return promiseService.get(userId);
    }

    @PutMapping
    public List<PromiseListDto> update(@Valid @RequestBody PromiseUpdateDto promiseUpdateDto, BindingResult result) {

        validation(result);
        return promiseService.update(promiseUpdateDto);
    }

    @DeleteMapping
    public List<PromiseListDto> delete(@RequestParam(value = "promise-id") Long promiseId, HttpSession session) {

        return promiseService.delete(promiseId , session);
    }

    private void validation(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> allErrors = result.getFieldErrors();
            StringBuilder errors = new StringBuilder();

            for (FieldError field : allErrors) {
                errors.append(field.getField()).append(": ").append(field.getDefaultMessage());
            }
            throw new FieldValidationPromiseException(String.valueOf(errors));
        }
    }

}
