package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.InvalidWineIdException;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public List<Wine> listAllWines() {
        return this.wineRepository.findAll();
    }

    @Override
    public Wine findById(Long id) {
        return this.wineRepository.findById(id).orElseThrow(() -> new InvalidWineIdException(id));
    }

    @Override
    public Wine create(String name, Double price, Integer quality, Category category, Manufacturer manufacturer) {
        Wine wine = new Wine(name, price, quality, category, manufacturer);
        return this.wineRepository.save(wine);
    }

    @Override
    public Wine update(Long id, String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        Wine wine = this.findById(id);
        wine.setName(name);
        wine.setPrice(price);
        wine.setQuantity(quantity);
        wine.setCategory(category);
        wine.setManufacturer(manufacturer);

        return this.wineRepository.save(wine);
    }

    @Override
    public Wine delete(Long id) {
        Wine wine = this.findById(id);
        this.wineRepository.delete(wine);
        return wine;
    }

    @Override
    public List<Wine> listWinesByNameAndCategory(String name, Long categoryId) {
        // TODO: implement this function
        return new ArrayList<>();
    }
}
