package com.kindsonthegenius.fleetmsv2.security.services;

import com.kindsonthegenius.fleetmsv2.security.models.User;
import com.kindsonthegenius.fleetmsv2.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
