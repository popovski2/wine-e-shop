package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.CategoryNotFoundException;
import com.petarangela.wineeshop.model.exceptions.InvalidCategoryIdException;
import com.petarangela.wineeshop.repository.CategoryRepository;
import com.petarangela.wineeshop.repository.TypeRepository;
import com.petarangela.wineeshop.repository.WineRepository;
import com.petarangela.wineeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    private final WineRepository wineRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, TypeRepository typeRepository, WineRepository wineRepository) {
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
        this.wineRepository = wineRepository;
    }




    /** FIND CATEGORY BY ID
     * @return*/
    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new InvalidCategoryIdException(id));
    }

    /** LIST ALL CATEGORIES */
    @Override
    public List<Category> listAll() {

        return this.categoryRepository.findAll();
    }

    /** CREATE CATEGORY
     * @return*/
    @Override
    public Category create(String name) {

        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
     //   List<Type> types = this.typeRepository.findAllById(typesId);
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }

    /** UPDATE CATEGORY
     * @return*/
    @Override
    public Category update(Long id, String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
      //  List<Type> types = this.typeRepository.findAllById(typesId);

        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        category.setName(name);
        return this.categoryRepository.save(category);
    }


    /** DELETE CATEGORY */
    @Override
    public Category delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findCategoryByName(name);

        List<Type> types = this.typeRepository.findAll();
        types = types.stream().filter(t -> t.getCategory().getId().equals(category.getId())).collect(Collectors.toList());

        List<Wine> wines = this.wineRepository.findAll();
        for (Wine w : wines) {
            for (Type t : types) {
                if(w.getType().getId().equals(t.getId()))
                    this.wineRepository.delete(w);
            }
        }

        for (Type t : types) {
            this.typeRepository.delete(t);
        }

        this.categoryRepository.delete(category);
        return category;
        //this.categoryRepository.deleteByName(name);
    }


    /** SEARCH CATEGORIES */
    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }

    @Override
    public List<Type> listAllTypes(String name) {
       return this.typeRepository.findAllByCategory_Name(name);
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.findCategoryByName(name);
    }

}
