package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    //User findByEmail(String email);

    //User findByPassword(String password);


}
