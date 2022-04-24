package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.exceptions.CategoryNotFoundException;
import com.petarangela.wineeshop.model.exceptions.InvalidTypeIdException;
import com.petarangela.wineeshop.repository.CategoryRepository;
import com.petarangela.wineeshop.repository.TypeRepository;
import com.petarangela.wineeshop.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;

    public TypeServiceImpl(TypeRepository typeRepository, CategoryRepository categoryRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Type> listAllTypes() {
        return this.typeRepository.findAll();
    }

    @Override
    public Type findById(Long id) {
        return this.typeRepository.findById(id).orElseThrow(() -> new InvalidTypeIdException(id));
    }

    @Override
    public Type create(String name, String description, Long categoryId) {
        if (name==null || name.isEmpty()) {
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
        Type type = new Type(name,description,category);
        return this.typeRepository.save(type);
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.typeRepository.deleteByName(name);
    }

    @Override
    public List<Type> findAllByCategoryName(String name) {
        return this.typeRepository.findAllByCategory_Name(name);
    }


}
