package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
