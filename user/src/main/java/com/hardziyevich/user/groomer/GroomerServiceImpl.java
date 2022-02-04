package com.hardziyevich.user.groomer;

import com.hardziyevich.user.entity.Role;
import com.hardziyevich.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroomerServiceImpl implements GroomerService {

    private static final String DELIMITER = " ";

    private final UserRepository userRepository;

    public GroomerServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> findALlGroomers() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole().equals(Role.GROOMER))
                .map(u -> String.join(DELIMITER, u.getFirstName(), u.getLastName()))
                .toList();
    }

    @Override
    public List<String> findAllGroomerByListId(List<Long> groomerId) {
        return userRepository.findAllById(groomerId).stream()
                .filter(g -> g.getRole().equals(Role.GROOMER))
                .map(u -> String.join(DELIMITER, u.getFirstName(), u.getLastName()))
                .toList();
    }

    @Override
    public Optional<Long> findGroomerIdByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findUserIdByFirstNameAndLastName(firstName, lastName, Role.GROOMER);
    }

}
