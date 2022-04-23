package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wines")
public class WineController {


    private final WineService service;

    public WineController(WineService service) {
        this.service = service;
    }


    public String showProducts(String nameSearch, Long categoryId) {
        if (nameSearch == null && categoryId == null) {
            this.service.listAllWines();
        } else {
            this.service.listWinesByNameAndCategory(nameSearch,categoryId);
        }
        return "";
    }

    public String showAdd() {

        return "";
    }

    public String showEdit(Long id) {
        this.service.findById(id);
        return "";
    }


    public String create(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        this.service.create(name,price,quantity,category,manufacturer);
        return "";
    }

    public String update(Long id, String name, Double price, Integer quantity, Category category,Manufacturer manufacturer) {
        this.service.update(id,name,price,quantity,category,manufacturer);
        return "";
    }

    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }



}
