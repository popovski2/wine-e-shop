package com.petarangela.wineeshop;

import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.UserRole;
import com.petarangela.wineeshop.service.UserService;
import com.petarangela.wineeshop.web.controllers.LoginController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession
public class WineEShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineEShopApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() throws UnsupportedEncodingException {
		return new BCryptPasswordEncoder(11, new SecureRandom("seed".getBytes("UTF-8")));
	}
/*
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}*/
	@Bean
	public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
		return new RedisHttpSessionConfiguration();
	}
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")
						.allowedHeaders("*")
						//.allowedOrigins("*")
						.allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}
*/



	@Bean
	public CorsFilter corsFilter(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
				"Accept","Authorization","Origin, Accept","X-Request-With","Access-Control-Request-Method",
				"Access-Control-Request-Headers"));

		corsConfiguration.setExposedHeaders(Arrays.asList("Origin","Content-Type","Accept","Authorization",
				"Access-Control-Allow-Origin","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));

		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
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
