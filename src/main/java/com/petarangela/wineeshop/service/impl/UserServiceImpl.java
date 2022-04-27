package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.UserRole;
import com.petarangela.wineeshop.model.exceptions.InvalidArgumentsException;
import com.petarangela.wineeshop.model.exceptions.InvalidUserCredentialsException;
import com.petarangela.wineeshop.repository.UserRepository;
import com.petarangela.wineeshop.repository.UserRoleRepository;
import com.petarangela.wineeshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository roleRepository,
                           PasswordEncoder passwordEncoder
                           ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("username");
        } else {
            log.info("User {} found in the database", username);
        }

        // looping over all the roles of the user and for every single one, we're creating a simple granted authority by passing the role name
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getUserRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the DB", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserRole saveRole(UserRole role) {
        log.info("Saving new role {} to the DB", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        UserRole role = roleRepository.findByName(roleName);
        user.getUserRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
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

     ********************************************************************************************************************/
    /* @Override
    public Optional<User> login(String email, String password) {
        if (email==null || email.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        //User user = this.userRepository.findByPassword(String password);
        return Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));

    }*/

    /********************************************************************************************************************

                                           CREATE USER === REGISTER USER

    *********************************************************************************************************************/
    @Override
    public User register(String username, String password, String name, String surname, List<UserRole> roles) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }


        //if(this.userRepository.findByEmail(email) == null){
        //    throw new EmailAlreadyExistsException(email);
        //}

        // KAKO TRET ARGUMENT PRAKJAME CUSTOMER ZA DA NE MOZE DA SE KREIRA NOV ADMIN
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(username,encryptedPassword, name, surname, roles);
        return userRepository.save(user);
    }


    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password);
    }



}
