package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RedisHash
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Category> findAllByNameLike(String name);

    Manufacturer findManufacturerByName(String name);

    void deleteByName(String name);

    void deleteById(Long id);
}
