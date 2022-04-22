package com.petarangela.wineeshop.model.exceptions;

public class InvalidCategoryIdException extends RuntimeException {
    public InvalidCategoryIdException(Long id){
        super(String.format("Category with id %d was not found", id));
    }
}

