package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;

import java.util.List;

public interface TypeService {

    // list all wines
    List<Type> listAllTypes();

    // find a type of wine by its id
    Type findById(Long id);

    // create a new type and save it to the DB
    Type create(String name, String description, Long categoryId);

    // update a type with specific id and save the changes to the DB
    Type update(Long id, String name, String description, Long categoryId );

    // delete a type with its name and update the DB
    Type delete(Long id);

    // find  all types that belong to certain category
    List<Type> findAllByCategoryName(String name);

    // list all wines that belong to this type
    List<Wine> listAllWines(Long typeId);


}
