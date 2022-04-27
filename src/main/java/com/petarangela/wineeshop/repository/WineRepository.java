package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash
public interface WineRepository extends JpaRepository<Wine, Long> {
}
