package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Category;

import java.util.List;

public interface CategoryService {

    // find a category by its id
    Category findById(Long id);

    // find all categories
    List<Category> listAll();

    // create a new category
    Category create(String name);

    // update a category
    Category update(String name);

    void delete(String name);


    //  search categories
    List<Category> searchCategories(String searchText);
}
