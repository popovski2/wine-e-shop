package com.petarangela.wineeshop.config;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Role;
import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final UserService userService;

    private final CategoryService categoryService;

    private final WineService wineService;

    private final ManufacturerService manufacturerService;

    private final TypeService typeService;

    public DataInitializer(UserService userService, CategoryService categoryService, WineService wineService, ManufacturerService manufacturerService, TypeService typeService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.wineService = wineService;
        this.manufacturerService = manufacturerService;
        this.typeService = typeService;
    }


    @PostConstruct
    public void initData() {
        User admin = this.userService.create(ADMIN, ADMIN, Role.ROLE_ADMIN);

        List<Category> categories= new ArrayList<>();
        List<Manufacturer> manufacturers = new ArrayList<>();

        /** CREATE CATEGORIES */
        for (int i = 1; i < 6; i++) {
            Category category = new Category("Category" + i);
            categories.add(category);

            this.categoryService.create(category.getName());
        }

        /** CREATE MANUFACTURERS */
        for (int i = 1; i < 11; i++) {
            Manufacturer manufacturer = new Manufacturer("Manufacturer " + i, "Address" + i);
            manufacturers.add(manufacturer);
            this.manufacturerService.save(manufacturer.getName(),manufacturer.getAddress());
        }

        /** CREATE TYPES OF WINE */
        for (int i = 1; i < 6; i++) {

            this.typeService.create("Type of wine"+i,"Description for type: " + i);
        }


        for (int i = 1; i < 11; i++) {
            this.wineService.create("Wine " + i,
                    20.9 * i,
                    i*i,
                     i % 5L + 1,
                     i % 5L + 1,
                            i % 5L + 1);
                  //  categories.get(i).getId(),
                  //  manufacturers.get(i).getId());
        }
    }
}
