package com.petarangela.wineeshop.service.impl;

import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.model.exceptions.InvalidTypeIdException;
import com.petarangela.wineeshop.repository.TypeRepository;
import com.petarangela.wineeshop.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public List<Type> listAllTypes() {
        return this.typeRepository.findAll();
    }

    @Override
    public Type findById(Long id) {
        return this.typeRepository.findById(id).orElseThrow(() -> new InvalidTypeIdException(id));
    }

    @Override
    public Type create(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Type type = new Type(name,description);
        return this.typeRepository.save(type);
    }

    @Override
    public Type update(Long id, String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Type type = new Type(name,description);
        return this.typeRepository.save(type);
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.typeRepository.deleteByName(name);
    }
}
