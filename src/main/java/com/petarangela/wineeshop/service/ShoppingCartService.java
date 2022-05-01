package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.ShoppingCart;
import com.petarangela.wineeshop.model.Wine;

import java.util.List;

public interface ShoppingCartService {

    List<Wine> listAllWinesInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addWinesToShoppingCart(String username, Long wineId);

    void deleteFromShoppingCart(String username, Long wineId);

    void emptyShoppingCart(String username);

    void increaseQuantity(String username, Long wineId);

    void decreaseQuantity(String username, Long wineId);


}
