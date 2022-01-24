package com.hardziyevich.user.controller;


import com.hardziyevich.user.service.GroomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groomer")
public class GroomerController {

    private final GroomerService groomerService;

    public GroomerController(GroomerService groomerService) {
        this.groomerService = groomerService;
    }

    @GetMapping
    public List<String> showAllGroomer() {
        return groomerService.findALlGroomers();
    }
}
