package com.hardziyevich.gateway.groomer;

import java.util.List;

public interface GroomerService {
    List<String> findAllGroomer();
    List<String> findGroomerByFirstNameAndLastName(String firstName,String lastName);
}
