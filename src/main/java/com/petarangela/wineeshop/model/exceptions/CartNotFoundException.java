package com.petarangela.wineeshop.model.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long id){
        super(String.format("Cart with id %d was not found",id));
    }
}
