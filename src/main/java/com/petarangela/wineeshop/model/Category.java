package com.petarangela.wineeshop.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private List<Type> types;


   /* public Category(String name, List<Type> types) {
        this.name = name;
        this.types = types;
    }*/

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Type> getTypes() {
        return types;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
