package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    // find all categories
    List<Category> listAll();

    // find a category by its id
    Optional<Category> findById(Long id);

    //find category by name
    Category findByName(String name);

    // create a new category
    Category create(String name);

    // update a category
    Category update(Long id, String name);

    void deleteByName(String name);

    void deleteById(Long id);

    //  search categories
    List<Category> searchCategories(String searchText);

    // list all types of certain category (name is the name of the category)
    List<Type> listAllTypes(Long id);

}
