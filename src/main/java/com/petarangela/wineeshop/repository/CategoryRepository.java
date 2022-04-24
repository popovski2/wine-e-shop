package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameLike(String text);

   // void deleteByName(String name);

    Category findCategoryByName(String name);
}
