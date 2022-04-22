package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;

public interface UserService {
    // a function for creating a new user
    // returns the newly created user
    User create(String username, String password, Role role);
}
