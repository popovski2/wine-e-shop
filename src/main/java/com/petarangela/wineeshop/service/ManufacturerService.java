package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

    Manufacturer update(Long id, String name, String address);

    Manufacturer create(String name, String address);

    void deleteById(Long id);

    //find category by name
    Optional<Manufacturer> findByName(String name);


}
