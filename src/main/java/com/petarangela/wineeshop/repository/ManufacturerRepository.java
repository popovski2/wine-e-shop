package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
