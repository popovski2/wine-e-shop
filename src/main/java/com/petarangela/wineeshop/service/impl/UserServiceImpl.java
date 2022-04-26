package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.exceptions.InvalidArgumentsException;
import com.petarangela.wineeshop.model.exceptions.InvalidUserCredentialsException;
import com.petarangela.wineeshop.model.exceptions.EmailAlreadyExistsException;
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
/*
   @Override
    public User create(String username, String password, String name, String surname, Role role) {
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(username, encryptedPassword, name, surname, role);

        return this.userRepository.save(user);
    }
*/


    /********************************************************************************************************************

                                                    LOGIN USER

     *********************************************************************************************************************/
    @Override
    public User login(String email, String password) {
        if (email==null || email.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(InvalidUserCredentialsException::new);
    }







    /********************************************************************************************************************

                                           CREATE USER === REGISTER USER

    *********************************************************************************************************************/
    @Override
    public User register(String email, String password, String name, String surname, Role role) {
        if (email==null || email.isEmpty()  || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }


        if(this.userRepository.findByEmail(email).isPresent() || !this.userRepository.findByEmail(email).isEmpty()){
            throw new EmailAlreadyExistsException(email);
        }

        // KAKO TRET ARGUMENT PRAKJAME CUSTOMER ZA DA NE MOZE DA SE KREIRA NOV ADMIN
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(email,encryptedPassword, name, surname, role);
        return userRepository.save(user);
    }


}
