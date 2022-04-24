package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.ShoppingCart;
import com.petarangela.wineeshop.model.Wine;

import java.util.List;

public interface ShoppingCartService {

    // return all items in a shopping cart
    List<Wine> listAllWinesInShoppingCart(Long cartId);

    // take the active cart
    ShoppingCart getActiveShoppingCart(String username);

    // add wine into cart (finding the active cart with the user's username)
    ShoppingCart addProductToShoppingCart(String username, Long productId);

}
