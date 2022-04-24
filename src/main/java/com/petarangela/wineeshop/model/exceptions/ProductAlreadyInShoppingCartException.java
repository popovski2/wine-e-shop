package com.petarangela.wineeshop.model.exceptions;

public class ProductAlreadyInShoppingCartException extends RuntimeException{
    public ProductAlreadyInShoppingCartException(Long wineId, String username){
        super(String.format("Wine with id %d already exists in %s's shopping cart", wineId, username));
    }
}
