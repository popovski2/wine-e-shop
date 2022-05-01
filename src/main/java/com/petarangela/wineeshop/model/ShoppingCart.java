package com.petarangela.wineeshop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCreated;

    private Double totalPrice;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Wine> wines;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart(LocalDateTime dataCreated, User user, ShoppingCartStatus status) {
        this.id = (long) (Math.random()*1000);
        this.dataCreated = dataCreated;
        this.user = user;
        this.wines = new ArrayList<>();
        this.status = status;
        this.totalPrice = 0.00;
    }

    public ShoppingCart(User user) {
        this.dataCreated = LocalDateTime.now();
        this.user = user;
        this.wines = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

    public ShoppingCart() {
        this.id = (long) (Math.random()*1000);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCreated() {
        return dataCreated;
    }

    public User getUser() {
        return user;
    }

    public List<Wine> getWines() {
        return wines;
    }

    public ShoppingCartStatus getStatus() {
        return status;
    }
}
