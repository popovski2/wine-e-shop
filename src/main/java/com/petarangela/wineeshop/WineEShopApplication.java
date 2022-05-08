package com.petarangela.wineeshop;

import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.service.UserService;
import com.petarangela.wineeshop.web.controllers.LoginController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;
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
	/*@Bean
	public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
		jedisConnectionFactory();
		redisTemplate();
		return new RedisHttpSessionConfiguration();
	}
	*/
	/*@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	*//*	RedisStandaloneConfiguration a = new RedisStandaloneConfiguration();
		a.setPort(6379);
		a.setHostName("localhost");
		return new JedisConnectionFactory(a);*//*
		JedisConnectionFactory jedisConnectionFactory = null;

		try {
			RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
			redisStandaloneConfiguration.setDatabase(0);
			redisStandaloneConfiguration.setHostName("localhost");
			redisStandaloneConfiguration.setPort(6379);

			jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);

			jedisConnectionFactory.getPoolConfig().setMaxTotal(50);
			jedisConnectionFactory.getPoolConfig().setMaxIdle(50);
		} catch (RedisConnectionFailureException e) {
			e.getMessage();
		}

		return jedisConnectionFactory;
	}
*/
	/*@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	RedisTemplate<String, Object> redisTemplate() {
	*//*	RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;*//*

			final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
			template.setConnectionFactory(jedisConnectionFactory());
			template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
			template.setEnableTransactionSupport(true);
			return template;

	}
*/

//	private RedisProperties redisProperties;
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		//log.info("redis host: "+redisProperties.getHost()+", port: "+redisProperties.getPort()+", password: "+redisProperties.getPassword());
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
//		redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
//		JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration
//				.builder()
//				.connectTimeout(redisProperties.getTimeout())
//				.readTimeout(redisProperties.getJedis().getPool().getMaxWait());
//		if (redisProperties.isSsl())
//			builder.useSsl();
//		// Final JedisClientConfiguration
//		JedisClientConfiguration clientConfig = builder.build();//.usePooling().build();
//		//TODO: Later: Add configurations for connection pool sizing.
//		//Create JedisConnectionFactory
//		return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfig);
//	}
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate() {
//		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
//		JedisConnectionFactory factory = jedisConnectionFactory();
//		template.setConnectionFactory(jedisConnectionFactory());
//		return template;
//	}
/*	  @Bean public RedisTemplate<String, Object> redisTemplate() {
		  RedisTemplate<String, Object> template = new RedisTemplate<>();
		  template.setConnectionFactory(jedisConnectionFactory());
		  return template;
	  }*/




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


}
