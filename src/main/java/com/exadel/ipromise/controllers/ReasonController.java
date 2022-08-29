package com.exadel.ipromise.controllers;


import com.exadel.ipromise.dto.ReasonDto;
import com.exadel.ipromise.exception.FieldValidationPromiseException;
import com.exadel.ipromise.service.ReasonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/reason")
public class ReasonController {

    private final ReasonService reasonService;

    public ReasonController(ReasonService reasonService) {
        this.reasonService = reasonService;
    }


    @PostMapping
    public ReasonDto create(@Valid @RequestBody ReasonDto reasonDto, BindingResult result) {

        validation(result);
        return reasonService.addReason(reasonDto);
    }

    @PutMapping
    public ReasonDto update(@Valid @RequestBody ReasonDto reasonDto, BindingResult result) {

        validation(result);
        return reasonService.update(reasonDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam(value = "reasonId") Long reasonId) {

        reasonService.delete(reasonId);
        return ResponseEntity.ok("Removal completed");
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
