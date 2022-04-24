package com.petarangela.wineeshop.web.rest;

import com.petarangela.wineeshop.service.ShoppingCartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartRESTController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartRESTController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

}