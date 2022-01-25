package com.hardziyevich.user.groomer;

import com.hardziyevich.user.entity.User;
import com.hardziyevich.user.groomer.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GroomerService {

    private static final String DELIMITER = " ";

    private final UserRepository userRepository;

    public GroomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> findALlGroomers() {
        return userRepository.findAll().stream()
                .map(u -> String.join(DELIMITER, u.getFirstName(),u.getLastName()))
                .toList();
    }

    public List<String> findAllGroomerByListId(List<Long> groomerId){
        return userRepository.findAllById(groomerId).stream()
                .map(u -> String.join(DELIMITER, u.getFirstName(),u.getLastName()))
                .toList();
    }
}
