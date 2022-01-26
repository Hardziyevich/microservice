package com.hardziyevich.user.groomer;

import com.hardziyevich.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u.id FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
    Optional<Long> findUserIdByFirstNameAndLastName(String firstName, String lastName);
}
