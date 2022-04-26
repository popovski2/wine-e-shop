package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.ShoppingCart;
import com.petarangela.wineeshop.model.ShoppingCartStatus;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.CartNotFoundException;
import com.petarangela.wineeshop.model.exceptions.ProductAlreadyInShoppingCartException;
import com.petarangela.wineeshop.model.exceptions.UserNotFoundException;
import com.petarangela.wineeshop.repository.ShoppingCartRepository;
import com.petarangela.wineeshop.repository.UserRepository;
import com.petarangela.wineeshop.service.ShoppingCartService;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final WineService wineService;

    public ShoppingCartServiceImplementation(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, WineService wineService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.wineService = wineService;
    }


    @Override
    public List<Wine> listAllWinesInShoppingCart(Long cartId) {
        /*if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new CartNotFoundException(cartId);*/
        ShoppingCart cart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        return cart.getWines();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String email) {

        User u = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));;

        return this.shoppingCartRepository
                .findByUserAndStatus(u, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(u);
                    return this.shoppingCartRepository.save(cart);
                });
    }


    @Override
    public ShoppingCart addProductToShoppingCart(String email, Long wineId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(email);
        //injektirame servis NE REPOSITORY
        Wine wine = this.wineService.findById(wineId);

        if(shoppingCart.getWines().stream()
                .filter(i->i.getId().equals(wineId))
                .collect(Collectors.toList()).size() > 0) {
            //togas imame vakov produkt vo taa kosnicka
            throw new ProductAlreadyInShoppingCartException(wineId,email);
        }

        shoppingCart.getWines().add(wine);
        return this.shoppingCartRepository.save(shoppingCart);

    }
}
