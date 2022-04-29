package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.ShoppingCart;
import com.petarangela.wineeshop.model.ShoppingCartStatus;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.CartNotFoundException;
import com.petarangela.wineeshop.model.exceptions.ProductAlreadyInShoppingCartException;
import com.petarangela.wineeshop.model.exceptions.UserNotFoundException;
import com.petarangela.wineeshop.model.exceptions.WineNotFoundException;
import com.petarangela.wineeshop.repository.ShoppingCartRepository;
import com.petarangela.wineeshop.repository.UserRepository;
import com.petarangela.wineeshop.service.ShoppingCartService;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new CartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getWines();
    }


    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username);

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addWinesToShoppingCart(String username, Long wineId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Wine wine = this.wineService.findById(wineId).orElseThrow(() -> new WineNotFoundException(wineId));
        if(shoppingCart.getWines()
                .stream().filter(i -> i.getId().equals(wineId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(wineId, username);
        shoppingCart.getWines().add(wine);
        return this.shoppingCartRepository.save(shoppingCart);
    }


}
