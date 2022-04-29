package com.petarangela.wineeshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Wine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String imageUrl;

    @OneToOne
    private Type type;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private User creator;

    public Wine() {}

    /**
     *  WE USE THIS CONSTRUCTOR IN THE create FUNCTIONALITY IN THE SERVICE
     * */
    public Wine(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer, Type type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public Wine(String name, Double price, Integer quantity, String imageUrl, Type type, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.type = type;
        this.category = category;
        this.manufacturer = manufacturer;
    }



    public Wine(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer, User creator) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public User getCreator() {
        return creator;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
