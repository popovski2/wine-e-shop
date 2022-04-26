package com.petarangela.wineeshop.web.rest;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRESTController {


    private final UserService userService;


    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody User user){

        String name  = user.getName();
        String surname = user.getSurname();
        String password = user.getPassword();
        String email = user.getEmail();

        //changing ROLE from null to CUSTOMER (we are not allowing the user to choose ROLE on frontend)pos
        user.setRole(Role.CUSTOMER);
        Role role = user.getRole();

        User newUser = this.userService.register(email,password,name,surname,role);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }


}
