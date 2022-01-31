package com.hardziyevich.user.user;

import com.hardziyevich.resource.dto.SaveUserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public Long saveUser(@RequestBody @Valid SaveUserDto userDto){
        return userService.findOrSaveUser(userDto);
    }

}
