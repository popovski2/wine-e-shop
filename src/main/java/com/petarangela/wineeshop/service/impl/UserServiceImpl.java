package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.repository.UserRepository;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

   @Override
    public User create(String username, String password, Role role) {
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(username, encryptedPassword, role);

        return this.userRepository.save(user);
    }

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
