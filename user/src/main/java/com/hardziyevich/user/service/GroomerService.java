package com.hardziyevich.user.service;

import com.hardziyevich.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroomerService {

    private final UserRepository userRepository;

    public GroomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> findALlGroomers() {
        return userRepository.findAll().stream()
                .map(u -> String.join(" ", u.getFirstName(),u.getLastName()))
                .toList();
    }
}
