package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.InvalidManufacturerIdException;
import com.petarangela.wineeshop.repository.ManufacturerRepository;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    /** FIND  MANUFACTURER BY ID
     * @return*/
    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(() -> new InvalidManufacturerIdException(id));
    }

    /** SAVE MANUFACTURER (UPDATE)
     * @return*/
    @Override
    public Manufacturer save(String name, String address) {
        return this.manufacturerRepository.save(new Manufacturer(name,address));
    }

    /** CREATE MANUFACTURER
     * @return*/
    @Override
    public Manufacturer create(String name, String address) {
        Manufacturer manufacturer = new Manufacturer(name, address);
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

}
