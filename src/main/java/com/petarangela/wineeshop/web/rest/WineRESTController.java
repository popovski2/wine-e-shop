package com.petarangela.wineeshop.web.rest;


import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wines")
public class WineRESTController {

    public WineRESTController(WineService wineService) {
        this.wineService = wineService;
    }

    private final WineService wineService;


    /** WORKS !*/
    @GetMapping("/all")
    public ResponseEntity<List<Wine>> getAllWines(){
        List<Wine> wines = this.wineService.listAllWines();
        return new ResponseEntity<>(wines, HttpStatus.OK);
    }

  /*  *//** WORKS !*//*
    @GetMapping("/find/{id}")
    public ResponseEntity<Wine> getWineById(@PathVariable("id") Long id){
        Optional<Wine> wine = this.wineService.findById(id);
        return new ResponseEntity<>(wine, HttpStatus.OK);
    }
*/

    /**  WORKS !*/
    @PostMapping("/add")
    public ResponseEntity<Wine> save(   @RequestParam String name,
                                        @RequestParam Double price,
                                        @RequestParam Integer quantity,
                                        @RequestParam String imageUrl,
                                        @RequestParam Long categoryId,
                                        @RequestParam Long manufacturerId,
                                        @RequestParam Long typeId
                                        ){


     /*  return this.wineService.create(name,price,quantity,categoryId,manufacturerId,typeId)
                .map(wine -> ResponseEntity.ok().body(wine))
                .orElseGet(() -> ResponseEntity.badRequest().build());
*/
        Wine newWine = this.wineService.create(name, price, quantity, imageUrl, categoryId, manufacturerId, typeId);
        System.out.println(newWine);
       return new ResponseEntity<>(newWine,HttpStatus.CREATED);
    }

    /**  I VAKA RABOTIT SAMO VO POSTMAN MORAT DA MU DAJS CELA CATEGORY, MANUFACTURER I TYPE
     *   CELO MISLAM NA PRIMER VAKA
     *   "type": {
     *             "id": 2,
     *             "name": "Type of wine2",
     *             "description": "Description for type: 2"
     *         },
     *
     *         */
    @PostMapping("/addWine")
    public ResponseEntity<Wine> addWine(@RequestBody Wine wine){

        Wine newWine = this.wineService.create(
                wine.getName(),
                wine.getPrice(),
                wine.getQuantity(),
                wine.getImageUrl(),
                wine.getCategory().getId(),
                wine.getManufacturer().getId(),
                wine.getType().getId());
        System.out.println(newWine);
        return new ResponseEntity<>(newWine,HttpStatus.CREATED);
    }


    /** WORKS !*/
    @PutMapping("/update/{id}")
    public ResponseEntity<Wine> updateWine(@PathVariable Long id,
                                           @RequestParam String name,
                                           @RequestParam Double price,
                                           @RequestParam Integer quantity,
                                           @RequestParam String imageUrl,
                                           @RequestParam Long categoryId,
                                           @RequestParam Long manufacturerId,
                                           @RequestParam Long typeId){


        Wine updatedWine = this.wineService.update(id,name,price,quantity,imageUrl,categoryId,manufacturerId,typeId);
        return new ResponseEntity<>(updatedWine,HttpStatus.OK);
    }

    /** WORKS !*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWine(@PathVariable("id") Long id){
        this.wineService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
