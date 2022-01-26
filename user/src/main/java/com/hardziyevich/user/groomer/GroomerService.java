package com.hardziyevich.user.groomer;

import java.util.List;
import java.util.Optional;

public interface GroomerService {

    List<String> findALlGroomers();

    List<String> findAllGroomerByListId(List<Long> groomerId);

    Optional<Long> findGroomerIdByFirstNameAndLastName(String firstName, String lastName);
}
