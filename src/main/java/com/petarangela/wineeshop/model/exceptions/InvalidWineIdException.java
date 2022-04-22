package com.petarangela.wineeshop.model.exceptions;

public class InvalidWineIdException extends RuntimeException{
    public InvalidWineIdException(Long id){
        super(String.format("Category with id %d was not found", id));
    }
}
