package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.dto.WineDto;

import java.util.List;
import java.util.Optional;

public interface WineService {

    // list all wines
    List<Wine> listAllWines();

    // find a wine by its id
    Optional<Wine> findById(Long id);

    // create a new wine and save it to the DB
    Wine create(String name, Double price, Integer quantity, String url, Long categoryId, Long manufacturerId, Long typeId);

    // update a wine with specific id and save the changes to the DB
    Wine update(Long id, String name, Double price, Integer quantity, String imageUrl, Long categoryId, Long manufacturerId, Long typeId);

    Optional<Wine> update(Long id, WineDto productDto);

    // delete a wine with specific id and update the DB
    Wine delete(Long id);

    // filter the wines according to the requirements
    List<Wine> listWinesByNameAndCategory(String name, Long categoryId);

    // find all wines that have specific TYPE ID
    //List<Wine> listWinesByType(Long typeId);
    void deleteById(Long id);

    List<Wine> listWinesByCategory(String name);
}
