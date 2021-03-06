package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.CategoryNotFoundException;
import com.petarangela.wineeshop.model.exceptions.InvalidTypeIdException;
import com.petarangela.wineeshop.model.exceptions.TypeNotFoundException;
import com.petarangela.wineeshop.repository.CategoryRepository;
import com.petarangela.wineeshop.repository.TypeRepository;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;
    private final WineRepository wineRepository;

    public TypeServiceImpl(TypeRepository typeRepository, CategoryRepository categoryRepository, WineRepository wineRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
        this.wineRepository = wineRepository;
    }


    @Override
    public List<Type> listAllTypes() {
        return this.typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return Optional.ofNullable(this.typeRepository.findById(id).orElseThrow(() -> new InvalidTypeIdException(id)));
    }

    @Override
    public Optional<Type> findByName(String name) {
        return Optional.ofNullable(this.typeRepository.findTypeByName(name));
    }

    @Override
    public Type create(String name, String description, Long categoryId) {
        if (name==null || name.isEmpty() || description==null || description.isEmpty() || categoryId == null) {
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Type type = new Type(name,description,category);
        return this.typeRepository.save(type);
    }

    @Override
    public Type update(Long id, String name, String description, Long categoryId) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Type type = this.typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));

        type.setName(name);
        type.setDescription(description);
        type.setCategory(category);

        return this.typeRepository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Type> type = this.findById(id);
        List<Wine> wines = this.wineRepository.findAll();
        wines = wines.stream()
                .filter(w -> w.getType().getId().equals(id)).
                collect(Collectors.toList());
        this.wineRepository.deleteAll(wines);

        this.typeRepository.deleteById(type.get().getId());
    }


    @Override
    public List<Type> findAllByCategoryId(Long id) {
        return this.typeRepository.findAllByCategory_Id(id);
    }


    /**  LIST ALL WINES THAT BELONG TO SPECIFIC TYPE*/
    public List<Wine> listAllWines(Long typeId){
        Type type = this.typeRepository.findById(typeId).orElseThrow(() -> new TypeNotFoundException(typeId));
        return  this.wineRepository.findAllByType(type);

    }


}
