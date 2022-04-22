package com.petarangela.wineeshop.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shop_user")
public class User {

    @Id
    private String username;

    private String password;

    @Enumerated
    private Role role;

    public User(){}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
