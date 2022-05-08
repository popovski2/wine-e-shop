package com.petarangela.wineeshop.web.controllers;


import com.google.gson.Gson;
import com.petarangela.wineeshop.model.ShoppingCart;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("wines", this.shoppingCartService.listAllWinesInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        model.addAttribute("totalPrice", this.shoppingCartService.computeTotalPrice(shoppingCart.getId()));
        //model.addAttribute("total", 0.00);
        return "shopping-cart";
    }

    @PostMapping("/add-wine/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication, Model model) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addWinesToShoppingCart(user.getUsername(), id);
            //ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
            //model.addAttribute("total", shoppingCart.getTotalPrice());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWine(@PathVariable Long id, Authentication authentication, Model model) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.deleteFromShoppingCart(user.getUsername(), id);
            //ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
            //model.addAttribute("total", shoppingCart.getTotalPrice());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/emptyCart")
    public String EmptyCart(Authentication authentication, Model model) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.emptyShoppingCart(user.getUsername());
            //ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
            //model.addAttribute("total", shoppingCart.getTotalPrice());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @PostMapping("/increaseQuantity/{id}")
    public String IncreaseQuantity(@PathVariable Long id, Authentication authentication, Model model){
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.increaseQuantity(user.getUsername(), id);
            //ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
            //model.addAttribute("total", shoppingCart.getTotalPrice());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @PostMapping("/decreaseQuantity/{id}")
    public String DecreaseQuantity(@PathVariable Long id, Authentication authentication, Model model){
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.decreaseQuantity(user.getUsername(), id);
            //ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
            //model.addAttribute("total", shoppingCart.getTotalPrice());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }


}
