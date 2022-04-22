package com.petarangela.wineeshop.model.exceptions;

public class InvalidManufacturerIdException extends RuntimeException {
    public InvalidManufacturerIdException(Long id){
        super(String.format("Category with id %d was not found", id));
    }
}
