package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {

    void deleteByName(String name);
}
