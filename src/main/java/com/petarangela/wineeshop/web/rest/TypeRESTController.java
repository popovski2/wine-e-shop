package com.petarangela.wineeshop.web.rest;

import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeRESTController {

    private final TypeService typeService;

    public TypeRESTController(TypeService typeService) {
        this.typeService = typeService;
    }

    // WORKS !
    @GetMapping("/all")
    public ResponseEntity<List<Type>> getAllTypes(){
        List<Type> types = this.typeService.listAllTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    /** GET ALL WINES THAT BELONG TO THIS TYPE */
    @GetMapping("/allWines/{typeId}")
    public ResponseEntity<List<Wine>> getAllWines(@PathVariable Long typeId){
        List<Wine> wines = this.typeService.listAllWines(typeId);
        return new ResponseEntity<>(wines, HttpStatus.OK);
    }

    // WORKS !
    @GetMapping("/find/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable("id") Long id){
        Type type = this.typeService.findById(id);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    // WORKS !
    @PostMapping("/add")
    public ResponseEntity<Type> save(   @RequestParam String name,
                                        @RequestParam String description,
                                        @RequestParam Long categoryId
    ){
        Type newType = this.typeService.create(name, description, categoryId);
        System.out.println(newType);
        return new ResponseEntity<>(newType, HttpStatus.CREATED);
    }

    // WORKS !
    @PutMapping("/update/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String description,
                                           @RequestParam(required = false) Long categoryId) {


        Type updatedType = this.typeService.update(id, name, description, categoryId);
        return new ResponseEntity<>(updatedType,HttpStatus.OK);
    }

    // WORKS !
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteType(@PathVariable("id") Long id){
        this.typeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
