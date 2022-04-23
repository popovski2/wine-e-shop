package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;

public interface UserService {
    // a function for creating a new user
    // returns the newly created user
    User create(String username, String password, Role role);

    User login(String username, String password);

    /*
     DA ODLUCIME DALI KE JA KORISTIME CREATE ILI REGISTER FUNKCIJATA
     */
    User register(String username, String password, String repeatPassword, String name, String surname);

}
