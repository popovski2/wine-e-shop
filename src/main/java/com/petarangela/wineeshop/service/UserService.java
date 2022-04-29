package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    // a function for creating a new user
    // returns the newly created user
    // User create(String username, String password, String name, String surname, Role role);

    User saveUser(User user);

    UserRole saveRole(UserRole role);

   // void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

    // Optional<User> login(String email, String password);

    User register(String email, String password, String name, String surname);

    User login(String username, String password);

}
