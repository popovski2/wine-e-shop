package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.CategoryNotFoundException;
import com.petarangela.wineeshop.model.exceptions.InvalidManufacturerIdException;
import com.petarangela.wineeshop.model.exceptions.ManufacturerNotFoundException;
import com.petarangela.wineeshop.repository.ManufacturerRepository;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final WineRepository wineRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, WineRepository wineRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.wineRepository = wineRepository;
    }

    /** FIND  ALL MANUFACTURERS */
    @Override
   // @Cacheable(value="Manufacturer")
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    /** FIND  MANUFACTURER BY ID
     * @return*/
    @Override
   // @Cacheable(value="Manufacturer", key="#id")
    public Optional<Manufacturer> findById(Long id) {
        return Optional.ofNullable(this.manufacturerRepository.findById(id).orElseThrow(() -> new InvalidManufacturerIdException(id)));
    }


    /** CREATE MANUFACTURER
     * @return*/
    @Override
    public Manufacturer create(String name, String address) {
        if (name==null || name.isEmpty() || address==null || address.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Manufacturer manufacturer = new Manufacturer(name, address);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Long id, String name, String address) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(id).orElseThrow(() -> new ManufacturerNotFoundException(id));
        manufacturer.setName(name);
        manufacturer.setAddress(address);
        return this.manufacturerRepository.save(manufacturer);
    }

    /** DELETE MANUFACTURER BY ID*/
    @Override
    public void deleteById(Long id) {
        List<Wine> wines = this.wineRepository.findAll();
        wines = wines.stream()
                .filter(w -> w.getType().getId().equals(id)).
                collect(Collectors.toList());
        this.wineRepository.deleteAll(wines);
        this.manufacturerRepository.deleteById(id);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        return Optional.ofNullable(this.manufacturerRepository.findManufacturerByName(name));
    }


}
