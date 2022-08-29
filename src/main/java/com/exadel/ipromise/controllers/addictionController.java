package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.AddictionListDto;
import com.exadel.ipromise.service.AddictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/addiction")
public class addictionController {

    private final AddictionService addictionService;

    public addictionController(AddictionService addictionService) {
        this.addictionService = addictionService;
    }

    @GetMapping
    public List<AddictionListDto> getAll() {
        return addictionService.getAddictions();
    }

}
