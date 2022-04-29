package com.petarangela.wineeshop.config;


import com.petarangela.wineeshop.filter.CustomUsernamePasswordAuthenticationProvider;
import com.petarangela.wineeshop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    @Autowired
    @Lazy
    private UserServiceImpl userDetailsService;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.cors().disable();
      /*  http.csrf().disable().authorizeRequests().antMatchers("/api/auth/authenticate")
                .permitAll().antMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);*/

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/assets/**", "/register", "/products",
                        "/api/**").permitAll()

                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("http://localhost:4200", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.inMemoryAuthentication()
//                .withUser("kostadin.mishev")
//                .password(passwordEncoder.encode("km"))
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("admin"))
//                .authorities("ROLE_ADMIN");
        auth.authenticationProvider(authenticationProvider);
    }




    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO: If you are implementing the security requirements, remove this following line
        //web.ignoring().antMatchers("/**");
       web.ignoring()
               .antMatchers("/h2/**");
               // .antMatchers("/api/wines/all")
                //.antMatchers("/api/wines/find/**")
              // .antMatchers("/api/auth/authenticate");


    }


}
