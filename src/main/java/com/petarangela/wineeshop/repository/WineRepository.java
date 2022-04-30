package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RedisHash
public interface WineRepository extends JpaRepository<Wine, Long> {

    // list all wines that have Type == type
    List<Wine> findAllByType(Type type);

    void deleteById(Long id);
}
