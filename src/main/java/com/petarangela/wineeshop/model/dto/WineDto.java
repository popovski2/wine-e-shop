package com.petarangela.wineeshop.model.dto;

import com.petarangela.wineeshop.model.Type;
import lombok.Data;

@Data
public class WineDto {

    private String name;

    private Double price;

    private Integer quantity;

    private Long category;

    private Long manufacturer;

    private Long type;

    public WineDto() {
    }

    public WineDto(String name, Double price, Integer quantity, Long category, Long manufacturer, Long type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Long manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
