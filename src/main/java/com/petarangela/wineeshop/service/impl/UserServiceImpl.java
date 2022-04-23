package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.exceptions.InvalidArgumentsException;
import com.petarangela.wineeshop.model.exceptions.InvalidUserCredentialsException;
import com.petarangela.wineeshop.model.exceptions.PasswordsDoNotMatchException;
import com.petarangela.wineeshop.model.exceptions.UsernameAlreadyExistsException;
import com.petarangela.wineeshop.repository.UserRepository;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /*
    CREATE USER === REGISTER USER
     */
   @Override
    public User create(String username, String password, Role role) {
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(username, encryptedPassword, role);

        return this.userRepository.save(user);
    }


    /*
    LOGIN USER
    */
    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    /*
    CREATE USER === REGISTER USER
    */
    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty()){
            throw new UsernameAlreadyExistsException(username);
        }

        // KAKO TRET ARGUMENT PRAKJAME CUSTOMER ZA DA NE MOZE DA SE KREIRA NOV ADMIN
        User user = new User(username,password,Role.CUSTOMER);
        return userRepository.save(user);
    }


}
