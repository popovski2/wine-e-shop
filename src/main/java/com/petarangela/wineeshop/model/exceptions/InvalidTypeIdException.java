package com.petarangela.wineeshop.model.exceptions;

public class InvalidTypeIdException extends RuntimeException {
    public InvalidTypeIdException(Long id){
        super(String.format("Type with id %d was not found", id));
    }

}
