package com.petarangela.wineeshop.config;

import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.service.CategoryService;
import com.petarangela.wineeshop.service.ManufacturerService;
import com.petarangela.wineeshop.service.UserService;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final UserService userService;

    private final CategoryService categoryService;

    private final WineService wineService;

    private final ManufacturerService manufacturerService;

    public DataInitializer(UserService userService, CategoryService categoryService, WineService wineService, ManufacturerService manufacturerService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.wineService = wineService;
        this.manufacturerService = manufacturerService;
    }


    @PostConstruct
    public void initData() {
        User admin = this.userService.create(ADMIN, ADMIN, Role.ROLE_ADMIN);


        /*for (int i = 1; i < 6; i++) {
            this.categoryService.create("Category " + i);
        }*/

        /*for (int i = 1; i < 11; i++) {
            this.manufacturerService.save("Manufacturer " + i, "Address" + i);
        }*/

        for (int i = 1; i < 11; i++) {
            this.wineService.create("Wine " + i, 20.9 * i, i, this.categoryService.create("Category " + i), this.manufacturerService.create("Manufacturer" + i, "Address" + i));
        }
    }
}
