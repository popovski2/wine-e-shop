package com.petarangela.wineeshop.service;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;

import java.util.List;

public interface CategoryService {

    // find a category by its id
    Category findById(Long id);

    // find all categories
    List<Category> listAll();

    // create a new category
    Category create(String name);

    // update a category
    Category update(Long id, String name);

    Category delete(String name);

    //  search categories
    List<Category> searchCategories(String searchText);

    // list all types of certain category (name is the  name of the category)
    List<Type> listAllTypes(String name);

    //find category by name
    Category findByName(String name);
}
