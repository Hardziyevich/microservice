package com.hardziyevich.user.user;

import com.hardziyevich.resource.dto.SaveUserDto;

public interface UserService {

    Long findOrSaveUser(SaveUserDto userDto);
}
