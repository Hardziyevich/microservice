package com.hardziyevich.gateway.groomer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groomer")
public class GroomerController {

    @PostMapping("/find")
    public List<String> findAllGroomer(@RequestBody @Valid RequestGroomerDto requestGroomerDto){
        return null;
    }
}
