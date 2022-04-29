package com.petarangela.wineeshop.web.rest;

import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerRESTService {

    private final ManufacturerService manufacturerService;

    public ManufacturerRESTService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    // WORKS !
    @GetMapping("/all")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }
/*
    // WORKS !
    @GetMapping("/find/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") Long id){
        Manufacturer manufacturer = this.manufacturerService.findById(id);
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    // WORKS !
    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name,
                                        @RequestParam String address
    ){
        Manufacturer newManufacturer = this.manufacturerService.create(name, address);
        System.out.println(newManufacturer);
        return new ResponseEntity<>(newManufacturer, HttpStatus.CREATED);
    }*/

  /*  // WORKS !
    @PutMapping("/update/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String address) {

        Manufacturer updatedManufacturer = this.manufacturerService.save(name, address);
        return new ResponseEntity<>(updatedManufacturer,HttpStatus.OK);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable("id") Long id){
        this.manufacturerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

