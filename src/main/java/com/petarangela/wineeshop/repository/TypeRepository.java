package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RedisHash
public interface TypeRepository extends JpaRepository<Type,Long> {

    void deleteByName(String name);

    List<Type> findAllByCategory_Name(String name);

    List<Type> findAllByCategory_Id(Long id);

    Type findTypeByName(String name);
}
