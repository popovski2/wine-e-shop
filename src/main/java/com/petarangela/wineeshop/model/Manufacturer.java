package com.petarangela.wineeshop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Manufacturer() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
