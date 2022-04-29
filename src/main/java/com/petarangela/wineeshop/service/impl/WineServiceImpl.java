package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.*;
import com.petarangela.wineeshop.repository.CategoryRepository;
import com.petarangela.wineeshop.repository.ManufacturerRepository;
import com.petarangela.wineeshop.repository.TypeRepository;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final TypeRepository typeRepository;


    public WineServiceImpl(WineRepository wineRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, TypeRepository typeRepository) {
        this.wineRepository = wineRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.typeRepository = typeRepository;
    }


    @Override
    public List<Wine> listAllWines() {
        return this.wineRepository.findAll();
    }

    @Override
    public Optional<Wine> findById(Long id) {
        return Optional.ofNullable(this.wineRepository.findById(id).orElseThrow(() -> new InvalidWineIdException(id)));
    }

    @Override
    public Wine create(String name, Double price, Integer quality, String url, Long categoryId, Long manufacturerId, Long typeId) {

        Type type = this.typeRepository.findById(typeId).orElseThrow(() -> new TypeNotFoundException(typeId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Wine wine = new Wine(name, price, quality, url, type, category, manufacturer);
        return this.wineRepository.save(wine);
    }

    @Override
    public Wine update(Long id, String name, Double price, Integer quantity, String imageUrl, Long categoryId, Long manufacturerId, Long typeId) {

        Type type = this.typeRepository.findById(typeId).orElseThrow(() -> new TypeNotFoundException(typeId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Wine wine = this.findById(id).orElseThrow(() -> new WineNotFoundException(id));
        wine.setName(name);
        wine.setPrice(price);
        wine.setQuantity(quantity);
        wine.setImageUrl(imageUrl);
        wine.setCategory(category);
        wine.setManufacturer(manufacturer);
        wine.setType(type);

        return this.wineRepository.save(wine);
    }

    @Override
    public Wine delete(Long id) {
        Wine wine = this.findById(id).orElseThrow(() -> new WineNotFoundException(id));
        this.wineRepository.delete(wine);
        return wine;
    }

    @Override
    public void deleteById(Long id){
         this.wineRepository.deleteById(id);
    }

    @Override
    public List<Wine> listWinesByNameAndCategory(String name, Long categoryId) {
        // TODO: implement this function
        return new ArrayList<>();
    }

}
