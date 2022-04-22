package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Long> {
}
