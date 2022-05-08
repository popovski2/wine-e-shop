package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.dto.WineDto;
import com.petarangela.wineeshop.model.exceptions.*;
import com.petarangela.wineeshop.repository.CategoryRepository;
import com.petarangela.wineeshop.repository.ManufacturerRepository;
import com.petarangela.wineeshop.repository.TypeRepository;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Cacheable(value="Wine")
    public List<Wine> listAllWines() {
        return this.wineRepository.findAll();
    }

    @Override
    //@Cacheable(value="Wine", key="#id")
    public Optional<Wine> findById(Long id) {
        return Optional.ofNullable(this.wineRepository.findById(id).orElseThrow(() -> new InvalidWineIdException(id)));
    }

    @Override
    @Transactional
    public Wine create(String name, Double price, Integer quality, String url, Long categoryId, Long manufacturerId, Long typeId) {

        Type type = this.typeRepository.findById(typeId).orElseThrow(() -> new TypeNotFoundException(typeId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Wine wine = new Wine(name, price, quality, url, type, category, manufacturer);
        return this.wineRepository.save(wine);
    }

    @Override
   // @CachePut(value="Wine", key="#id")
    @Transactional
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
    public Optional<Wine> update(Long id, WineDto wineDto) {
        Wine wine = this.wineRepository.findById(id).orElseThrow(() -> new WineNotFoundException(id));

        wine.setName(wineDto.getName());
        wine.setPrice(wineDto.getPrice());
        wine.setQuantity(wineDto.getQuantity());



        Category category = this.categoryRepository.findById(wineDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(wineDto.getCategory()));
        wine.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(wineDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(wineDto.getManufacturer()));
        wine.setManufacturer(manufacturer);

        Type type = this.typeRepository.findById(wineDto.getType())
                .orElseThrow(() -> new TypeNotFoundException(wineDto.getType()));
        wine.setType(type);

        return Optional.of(this.wineRepository.save(wine));
    }


    @Override
    @CacheEvict(value="Wine", key="#id")
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

    @Override
    public List<Wine> listWinesByCategory(String name) {
        return this.wineRepository.findAll().stream()
                .filter(w -> w.getCategory().getName().equals(name))
                .collect(Collectors.toList());
    }


}
