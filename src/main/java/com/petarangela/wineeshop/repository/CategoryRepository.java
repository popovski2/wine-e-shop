package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
