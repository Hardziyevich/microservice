package com.hardziyevich.user.user;

import com.hardziyevich.resource.dto.SaveUserDto;

import java.util.Optional;

public interface UserService {

    Optional<Long> findOrSaveUser(SaveUserDto userDto);
}
