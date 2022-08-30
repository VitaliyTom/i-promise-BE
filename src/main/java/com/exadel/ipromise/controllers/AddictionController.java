package com.exadel.ipromise.controllers;

import com.exadel.ipromise.dto.AddictionListDto;
import com.exadel.ipromise.service.AddictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/addiction")
public class AddictionController {

    private final AddictionService addictionService;

    public AddictionController(AddictionService addictionService) {
        this.addictionService = addictionService;
    }

    @GetMapping
    public ResponseEntity<List<AddictionListDto>> getAll() {

        return ResponseEntity.ok().body(addictionService.getAddictions());
    }

}
