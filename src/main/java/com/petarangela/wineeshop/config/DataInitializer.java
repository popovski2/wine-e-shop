package com.petarangela.wineeshop.config;

import com.petarangela.wineeshop.model.*;
import com.petarangela.wineeshop.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

     userService.saveUser(new User("popovski2","1234","petar","popovski",Role.ROLE_CUSTOMER));
     userService.saveUser(new User("admin@gmail.com","1234","trajko","trajkovski",Role.ROLE_ADMIN));
     userService.saveUser(new User("angela.madjar","1234","angela","madjar",Role.ROLE_CUSTOMER));



     /** CREATE CATEGORIES */
     this.categoryService.create("Red");
     this.categoryService.create("White");
     this.categoryService.create("Rose");
     this.categoryService.create("Sparkling");


     /** CREATE TYPES */
     // red
     this.typeService.create("Cabernet Sauvignon",
             "Cabernet Sauvignon is one of the world's most widely recognized red wine grape varieties. It is grown in nearly every major wine producing country among a diverse spectrum of climates from Australia and British Columbia, Canada to Lebanon's Beqaa Valley.",
             this.categoryService.findByName("Red").getId());
     this.typeService.create("Pinot Noir",
             "Pinot noir is a red wine grape variety of the species Vitis vinifera. The name may also refer to wines created predominantly from Pinot noir grapes. The name is derived from the French words for pine and black.",
             this.categoryService.findByName("Red").getId());
     this.typeService.create("Merlot",
             "Merlot is a dark blue–colored wine grape variety, that is used as both a blending grape and for varietal wines. The name Merlot is thought to be a diminutive of merle, the French name for the blackbird, probably a reference to the color of the grape.",
             this.categoryService.findByName("Red").getId());
     // white
     this.typeService.create("Chardonnay",
             "Chardonnay is a green-skinned grape variety used in the production of white wine. The variety originated in the Burgundy wine region of eastern France, but is now grown wherever wine is produced, from England to New Zealand.",
             this.categoryService.findByName("White").getId());
     this.typeService.create("Moscato",
             "Moscato wine is famous for its sweet flavors of peaches and orange blossom. The word Moscato (“moe-ska-toe”) is the Italian name for Muscat Blanc – one of the oldest wine grapes in the world!",
             this.categoryService.findByName("White").getId());
     this.typeService.create("Sauvignon Blanc",
             "Sauvignon blanc is a green-skinned grape variety that originates from the Bordeaux region of France.",
             this.categoryService.findByName("White").getId());
     //rose
     this.typeService.create("Grenache Rosé",
             "Before being an organic wine, Autrement must be a premium wine that is subject to the same high quality standards as all Gérard Bertrand wines. Consistency of quality is ensured by the blending of the different terroirs.",
             this.categoryService.findByName("Rose").getId());
     this.typeService.create("Syrah Rosé",
             "The Syrah's long, hot growing season is tempered by the cooler maritime climate to create an elegant rosé with notes of white pepper, peach and strawberry",
             this.categoryService.findByName("Rose").getId());
     //Sparkling
     this.typeService.create("Champagne",
             "Champagne is a sparkling wine originated and produced in the Champagne wine region of France..",
             this.categoryService.findByName("Sparkling").getId());
     this.typeService.create("Prosecco",
             "Prosecco is an Italian DOC or DOCG white wine produced in a large area spanning nine provinces in the Veneto and Friuli Venezia Giulia regions, and named after the village of Prosecco which is in the province of Trieste, Italy.",
             this.categoryService.findByName("Sparkling").getId());



     /** CREATE MANUFACTURERS */
     this.manufacturerService.create("Alois Legeder", "Via Casòn Hirschprunn 1");
     this.manufacturerService.create("Abbazia di Novacella", "Via Abbazia, 1 - Varna");
     this.manufacturerService.create("Barone Pizzini", "Via S. Carlo, 14, Provaglio d'Iseo");
     this.manufacturerService.create("Castello", "Via Umberto I', 9, Verduno");
     this.manufacturerService.create("Pecorari Pierpaolo", "Via Tommaseo, 56, San Lorenzo");
     this.manufacturerService.create("Notte Rossa", "Terre di Sava, 16 - Sava");
     this.manufacturerService.create("Tenuta delle Terre Nere", " Contrada, Strada Comunale Calderara");



     /** WINES */
     // red
     this.wineService.create("'Les Sources' 2016",
             25.00,
             10,
             "https://data.callmewine.com/imgprodotto/bordeaux-rouge-superieur-les-sources-chateau-jean-faux-2016_32843.jpg",
             this.categoryService.findByName("Red").getId(),
             this.manufacturerService.findByName("Alois Legeder").get().getId(),
             this.typeService.findByName("Cabernet Sauvignon").get().getId());
     this.wineService.create("'Mamuthone' 2020",
             50.00,
             15,
             "https://data.callmewine.com/imgprodotto/cannonau-mamuthone-sedilesu-2020_41781.jpg",
             this.categoryService.findByName("Red").getId(),
             this.manufacturerService.findByName("Barone Pizzini").get().getId(),
             this.typeService.findByName("Pinot Noir").get().getId());
        this.wineService.create("Poggio Le Volpi 2019",
                49.44,
                25,
                "https://data.callmewine.com/imgprodotto/roma-rosso-poggio-le-volpi-2019_34173.jpg",
                this.categoryService.findByName("Red").getId(),
                this.manufacturerService.findByName("Notte Rossa").get().getId(),
                this.typeService.findByName("Merlot").get().getId());
        this.wineService.create("'Querciantica' 2020",
                31.99,
                5,
                "https://data.callmewine.com/imgprodotto/lacrima-di-morro-dalba-superiore-querciantica-velenosi-2020_40296.jpg",
                this.categoryService.findByName("Red").getId(),
                this.manufacturerService.findByName("Castello").get().getId(),
                this.typeService.findByName("Merlot").get().getId());
        // white
        this.wineService.create("Grand Cru Olivier 2018",
                1222.88,
                20,
                "https://data.callmewine.com/imgprodotto/chevalier-montrachet-grand-cru-olivier-leflaive-2018_33556.jpg",
                this.categoryService.findByName("White").getId(),
                this.manufacturerService.findByName("Pecorari Pierpaolo").get().getId(),
                this.typeService.findByName("Chardonnay").get().getId());
        this.wineService.create("'LR' Colterenzio 2018",
                96.00,
                20,
                "https://data.callmewine.com/imgprodotto/bianco-riserva-lr-colterenzio-2018_43023.jpg",
                this.categoryService.findByName("White").getId(),
                this.manufacturerService.findByName("Pecorari Pierpaolo").get().getId(),
                this.typeService.findByName("Moscato").get().getId());
        this.wineService.create("'Graacher Himmelreich'",
                79.80,
                20,
                "https://data.callmewine.com/imgprodotto/riesling-mosel-spatlese-graacher-himmelreich-j-j-prum-2010_30990.jpg",
                this.categoryService.findByName("White").getId(),
                this.manufacturerService.findByName("Tenuta delle Terre Nere").get().getId(),
                this.typeService.findByName("Sauvignon Blanc").get().getId());
        // rose
        this.wineService.create("'Fashion Victim' Astoria",
                29.80,
                20,
                "https://data.callmewine.com/imgprodotto/ros%C3%A9-sparkling-extra-dry-fashion-victim-astoria_21778.jpg",
                this.categoryService.findByName("Rose").getId(),
                this.manufacturerService.findByName("Tenuta delle Terre Nere").get().getId(),
                this.typeService.findByName("Grenache Rosé").get().getId());
        this.wineService.create("Extra Dry Fiol 2020",
                31.80,
                20,
                "https://data.callmewine.com/imgprodotto/prosecco-ros%C3%A9-extra-dry-fiol-2020_33923.jpg",
                this.categoryService.findByName("Rose").getId(),
                this.manufacturerService.findByName("Alois Legeder").get().getId(),
                this.typeService.findByName("Syrah Rosé").get().getId());
        // Sparkling
        this.wineService.create("Magnum Bellavista 2016",
                101.80,
                20,
                "https://data.callmewine.com/imgprodotto/franciacorta-brut-millesimato-edizione-teatro-alla-scala-magnum-bellavista-2016_36444.jpg",
                this.categoryService.findByName("Sparkling").getId(),
                this.manufacturerService.findByName("Alois Legeder").get().getId(),
                this.typeService.findByName("Champagne").get().getId());
        this.wineService.create("'Belle Epoque' 2013",
                109.99,
                20,
                "https://data.callmewine.com/imgprodotto/champagne-ros%C3%A9-brut-belle-epoque-perrier-jouet-2013_37626.jpg",
                this.categoryService.findByName("Sparkling").getId(),
                this.manufacturerService.findByName("Barone Pizzini").get().getId(),
                this.typeService.findByName("Champagne").get().getId());
        this.wineService.create("'Dirupo' Magnum",
                29.99,
                20,
                "https://data.callmewine.com/imgprodotto/prosecco-di-valdobbiadene-superiore-extra-dry-dirupo-magnum-andreola_17357.jpg",
                this.categoryService.findByName("Sparkling").getId(),
                this.manufacturerService.findByName("Barone Pizzini").get().getId(),
                this.typeService.findByName("Prosecco").get().getId());
    }
}
