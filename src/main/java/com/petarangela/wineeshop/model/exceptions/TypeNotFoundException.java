package com.petarangela.wineeshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TypeNotFoundException extends RuntimeException {

    public TypeNotFoundException(Long id){
        super(String.format("Type of wine with id %d was not found",id));
    }

}
