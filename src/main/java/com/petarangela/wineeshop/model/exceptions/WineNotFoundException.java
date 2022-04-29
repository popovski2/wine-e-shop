package com.petarangela.wineeshop.model.exceptions;

public class WineNotFoundException extends RuntimeException {
    public WineNotFoundException(Long id) {
        super(String.format("Wine with id %d was not found", id));

    }
}
