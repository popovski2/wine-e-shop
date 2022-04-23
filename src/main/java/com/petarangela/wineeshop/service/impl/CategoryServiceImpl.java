package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.exceptions.InvalidCategoryIdException;
import com.petarangela.wineeshop.repository.CategoryRepository;
import com.petarangela.wineeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }




    /** FIND CATEGORY BY ID */
    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new InvalidCategoryIdException(id));
    }

    /** LIST ALL CATEGORIES */
    @Override
    public List<Category> listAll() {

        return this.categoryRepository.findAll();
    }

    /** CREATE CATEGORY */
    @Override
    public Category create(String name) {

        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }

    /** UPDATE CATEGORY */
    @Override
    public Category update(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }


    /** DELETE CATEGORY */
    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }


    /** SEARCH CATEGORIES */
    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
