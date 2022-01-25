package com.hardziyevich.user.groomer;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groomer")
public class GroomerController {

    private final GroomerService groomerService;

    public GroomerController(GroomerService groomerService) {
        this.groomerService = groomerService;
    }

    @GetMapping("/search")
    public List<String> showAllGroomer() {
        return groomerService.findALlGroomers();
    }

    @PostMapping("/groomerId")
    public List<String> showAllGroomerById(@RequestBody List<Long> groomerId) {
        System.out.println(groomerId);
        return groomerService.findAllGroomerByListId(groomerId);
    }
}
