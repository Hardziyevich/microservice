package com.hardziyevich.user.groomer;


import com.hardziyevich.resource.dto.PersonalDataGroomerDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groomer")
public class GroomerController {

    private static final Long WRONG_RESULT = 0L;
    private final GroomerService groomerService;

    public GroomerController(GroomerServiceImpl groomerService) {
        this.groomerService = groomerService;
    }

    @PostMapping ("/search")
    public List<String> showAllGroomer() {
        return groomerService.findALlGroomers();
    }

    @PostMapping("/groomerId")
    public List<String> showAllGroomerById(@RequestBody List<Long> groomerId) {
        return groomerService.findAllGroomerByListId(groomerId);
    }

    @PostMapping("/findGroomerIdByFirstNameAndLastName")
    public Long showAllGroomerIdByFirstNameAndLastName(@RequestBody @Valid PersonalDataGroomerDto request) {
        return groomerService.findGroomerIdByFirstNameAndLastName(request.getFirstName(), request.getLastName())
                .orElse(WRONG_RESULT);
    }
}
