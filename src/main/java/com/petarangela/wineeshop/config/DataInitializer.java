package com.petarangela.wineeshop.config;

import com.petarangela.wineeshop.model.*;
import com.petarangela.wineeshop.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        //User admin = this.userService.register("petarangela@gmail.com", ADMIN, ADMIN, ADMIN, Role.ROLE_ADMIN);
/*

        userService.saveRole(new UserRole(null, "ROLE_USER"));
        userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
        userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
*/

        ShoppingCart shoppingCart = new ShoppingCart();
        List<ShoppingCart> carts = new ArrayList<>();
        carts.add(shoppingCart);

     /*   userService.saveUser(new User(null, "Angela", "Madjar", "angela.madjar", "1234", new ArrayList<>(), carts));
        userService.saveUser(new User(null, "Petar", "Popovski", "petar.popovski", "1234", new ArrayList<>(), carts));
        userService.saveUser(new User(null, "Trajko", "Trajkoski", "trajko.trajkoski", "1234", new ArrayList<>(), carts));
*/
     userService.saveUser(new User("popovski2","1234","petar","popovski",Role.ROLE_CUSTOMER));
     userService.saveUser(new User("trajko","1234","trajko","trajkovski",Role.ROLE_ADMIN));
     userService.saveUser(new User("angela.madjar","1234","angela","madjar",Role.ROLE_CUSTOMER));




/*
        userService.addRoleToUser("angela.madjar", "ROLE_USER");
        userService.addRoleToUser("petar.popovski", "ROLE_MANAGER");
        userService.addRoleToUser("trajko.trajkoski", "ROLE_ADMIN");
        userService.addRoleToUser("trajko.trajkoski", "ROLE_USER");
        userService.addRoleToUser("trajko.trajkoski", "ROLE_MANAGER");*/

        List<Category> categories= new ArrayList<>();
        List<Manufacturer> manufacturers = new ArrayList<>();
        List<Type> types = new ArrayList<>();




        /** CREATE CATEGORIES */
        for (int i = 1; i < 6; i++) {
            Category category = new Category("Category" + i);
            //categories.add(category);


           //  GET ALL TYPES
           List<Type> all = this.typeService.listAllTypes();


           // MAP ALL TYPES TO THEIR IDS
           List<Long> allIds = all.stream().map(Type::getId).collect(Collectors.toList());


          this.categoryService.create(category.getName());
        }

        /** CREATE TYPES OF WINE */
        for (int i = 1; i < 6; i++) {
            this.typeService.create("Type of wine"+i,
                    "Description for type: " + i,
                    this.categoryService.listAll().get(i-1).getId());
        }

        /** CREATE MANUFACTURERS */
        for (int i = 1; i < 11; i++) {
            Manufacturer manufacturer = new Manufacturer("Manufacturer " + i, "Address" + i);
            manufacturers.add(manufacturer);
            this.manufacturerService.save(manufacturer.getName(),manufacturer.getAddress());
        }


        List<String> urls = new ArrayList<>();
        urls.add("https://8wines.com/media/catalog/product/cache/c7c042d8294616e945a4ef11616f811c/m/o/montelena_1_1.png");
        urls.add("https://8wines.com/media/catalog/product/cache/c7c042d8294616e945a4ef11616f811c/p/a/paradigm-cabernet-sauvignon-2016.png");
        urls.add("https://data.callmewine.com/imgprodotto/sancerre-domaine-vacheron-2020_36670.jpg");
        urls.add("https://8wines.com/media/catalog/product/cache/c7c042d8294616e945a4ef11616f811c/c/h/chateau-leoube-sparkling-de-leoube-rose.png");
        urls.add("https://data.callmewine.com/imgprodotto/etna-bianco-le-sabbie-delletna-firriato-2021_40784.jpg");
        urls.add("https://data.callmewine.com/imgprodotto/etna-bianco-alta-mora-cusumano-2020_38372.jpg");
        urls.add("https://data.callmewine.com/imgprodotto/champagne-brut-grande-reserve-potel-prieux_22511.jpg");
        urls.add("https://data.callmewine.com/imgprodotto/champagne-brut-r-ruinart_30255.jpg");
        urls.add("https://data.callmewine.com/imgprodotto/due-rosso-giannitessari-2019_36038.jpg");
        urls.add("https://data.callmewine.com/imgprodotto/achille-bindi-sergardi-2020_36331.jpg");
        urls.add("https://data.callmewine.com/imgprodotto/sangiovese-di-romagna-lona-bona-trer%C3%A8-2021_39226.jpg");


        for (int i = 1; i < 11; i++) {
            this.wineService.create("Wine " + i,
                    20.9 * i,
                    i*i,
                     urls.get(i),
                     i % 5L + 1,
                     i % 5L + 1,
                            i % 5L + 1);
                  //  categories.get(i).getId(),
                  //  manufacturers.get(i).getId());
        }
    }
}
