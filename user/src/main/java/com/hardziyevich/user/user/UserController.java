package com.hardziyevich.user.user;

import com.hardziyevich.resource.dto.SaveUserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Long WRONG_RESULT = 0L;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public Long saveUser(@RequestBody @Valid SaveUserDto userDto) {
        Optional<Long> result;
        try {
            result = userService.findOrSaveUser(userDto);
        } catch (Exception e) {
            result = Optional.of(WRONG_RESULT);
        }
        return result.orElse(WRONG_RESULT);
    }

}
