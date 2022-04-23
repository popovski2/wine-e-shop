package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Manufacturer findById(Long id);

    Optional<Manufacturer> save(String name, String address);

    Manufacturer create(String name, String address);

    void deleteById(Long id);


}
