package com.petarangela.wineeshop.web.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.UserRole;
import com.petarangela.wineeshop.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {

    private final UserService userService;

    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save/user").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/save/role")
    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

 /*   @PostMapping("/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
*/
   /* @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { // whenever we are going to send a request from Angular, we'll be sending the string Bearer + the token
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length()); // removing the Bearer
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject(); // gets the username that comes with the token
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 min
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", user.getUserRoles().stream()
                                .map(UserRole::getName)
                                .collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
*/

/*    @PostMapping("/registerUser")
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

    @PostMapping("/loginUser")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
       // User authenticatedUser =
         return this.userService.login(user.getEmail(), user.getPassword())
                 .map(u -> ResponseEntity.ok().body(u))
                 .orElseGet(() -> ResponseEntity.badRequest().build());
        //return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    }
*/

    @Data
    class RoleToUserForm {
        private String username;

        private String roleName;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

}
