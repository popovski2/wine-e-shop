package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.ShoppingCart;
import com.petarangela.wineeshop.model.ShoppingCartStatus;
import com.petarangela.wineeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}