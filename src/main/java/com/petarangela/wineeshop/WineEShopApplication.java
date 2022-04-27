package com.petarangela.wineeshop;

import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.UserRole;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;

@SpringBootApplication
public class WineEShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineEShopApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() throws UnsupportedEncodingException {
		return new BCryptPasswordEncoder(11, new SecureRandom("seed".getBytes("UTF-8")));
	}

	/* @Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new UserRole(null, "ROLE_USER"));
			userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
			userService.saveRole(new UserRole(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "Angela", "Madjar", "angela.madjar", "1234", new ArrayList<>(), new ArrayList<>()));
			userService.saveUser(new User(null, "Petar", "Popovski", "petar.popovski", "1234", new ArrayList<>(), new ArrayList<>()));
			userService.saveUser(new User(null, "Trajko", "Trajkoski", "trajko.trajkoski", "1234", new ArrayList<>(), new ArrayList<>()));

			userService.addRoleToUser("angela.madjar", "ROLE_USER");
			userService.addRoleToUser("petar.popovski", "ROLE_MANAGER");
			userService.addRoleToUser("trajko.trajkoski", "ROLE_ADMIN");
			userService.addRoleToUser("trajko.trajkoski", "ROLE_USER");
			userService.addRoleToUser("trajko.trajkoski", "ROLE_MANAGER");
		};
	}*/

}
