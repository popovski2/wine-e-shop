package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Wine;

import java.util.List;

public interface WineService {

    // list all wines
    List<Wine> listAllWines();

    // find a wine by its id
    Wine findById(Long id);

    // create a new wine and save it to the DB
    Wine create(String name, Double price, Integer quality, Category category, Manufacturer manufacturer);

    // update a wine with specific id and save the changes to the DB
    Wine update(Long id, String name, Double price, Integer quantity, Category category, Manufacturer manufacturer);

    // delete a wine with specific id and update the DB
    Wine delete(Long id);

    // filter the wines according to the requirements
    List<Wine> listWinesByNameAndCategory(String name, Long categoryId);

}
