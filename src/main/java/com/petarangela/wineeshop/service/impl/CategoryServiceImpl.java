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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    /** LIST ALL CATEGORIES */
    @Override
    public List<Category> listAll() {

        return this.categoryRepository.findAll();
    }

    /** FIND CATEGORY BY ID
     * @return*/
    @Override
   // @Cacheable(value="Category", key="#id")
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(this.categoryRepository.findById(id).orElseThrow(() -> new InvalidCategoryIdException(id)));
    }

    /** CREATE CATEGORY
     * @return*/
    @Override
    @Transactional
    public Category create(String name) {

        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }

    /** UPDATE CATEGORY
     * @return*/
    @Override
    @Transactional
    //@CachePut(value="Category", key="#id")
    public Category update(Long id, String name) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        category.setName(name);
        return this.categoryRepository.save(category);
    }


    /** DELETE CATEGORY */
    @Override
   // @CacheEvict(value="Category", key="#id")
    public void deleteByName(String name) {
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

        this.typeRepository.deleteAll(types);

        this.categoryRepository.delete(category);
        //this.categoryRepository.deleteByName(name);
    }

    @Override
    // @CacheEvict(value="Category", key="#id")
    public void deleteById(Long id) {

        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new InvalidCategoryIdException(id));

        List<Type> types = this.typeRepository.findAll();
        types = types.stream().filter(t -> t.getCategory().getId().equals(category.getId())).collect(Collectors.toList());

        List<Wine> wines = this.wineRepository.findAll();
        for (Wine w : wines) {
            for (Type t : types) {
                if(w.getType().getId().equals(t.getId()))
                    this.wineRepository.delete(w);
            }
        }
        this.typeRepository.deleteAll(types);

        this.categoryRepository.deleteById(id);
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
