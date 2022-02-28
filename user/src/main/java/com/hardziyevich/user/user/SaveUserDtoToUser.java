package com.hardziyevich.user.user;

import com.hardziyevich.resource.dto.SaveUserDto;
import com.hardziyevich.resource.mapper.Mapper;
import com.hardziyevich.user.entity.Role;
import com.hardziyevich.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SaveUserDtoToUser implements Mapper<User, SaveUserDto> {

    @Override
    public User mapTo(SaveUserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .role(Role.USER)
                .build();
    }

}
