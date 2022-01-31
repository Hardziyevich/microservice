package com.hardziyevich.user.user;

import com.hardziyevich.resource.dto.SaveUserDto;
import com.hardziyevich.user.entity.User;
import com.hardziyevich.user.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Long WRONG_RESULT = 0L;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Long findOrSaveUser(SaveUserDto userDto) {
        Long result;
        ExampleMatcher userMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("password")
                .withMatcher("first_name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("last_name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<User> userExample = Example.of(new SaveUserDtoToUser().mapTo(userDto), userMatcher);
        boolean exists = userRepository.exists(userExample);
        if (exists) {
            result = userRepository.findOne(userExample)
                    .map(User::getId)
                    .orElse(WRONG_RESULT);
        } else {
            result = userRepository.save(userExample.getProbe()).getId();
        }
        return result;
    }
}
