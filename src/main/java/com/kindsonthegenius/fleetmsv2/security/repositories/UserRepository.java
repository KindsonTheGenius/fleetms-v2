package com.kindsonthegenius.fleetmsv2.security.repositories;

import com.kindsonthegenius.fleetmsv2.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByFirstnameAndLastname(String firstName, String lastName);

    User findByEmail(String email);
}
