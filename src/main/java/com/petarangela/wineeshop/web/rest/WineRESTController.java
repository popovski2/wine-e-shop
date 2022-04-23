package com.petarangela.wineeshop.web.rest;


import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wines")
public class WineRESTController {

    public WineRESTController(WineService wineService) {
        this.wineService = wineService;
    }

    private final WineService wineService;


    @GetMapping("/all")
    public ResponseEntity<List<Wine>> getAllEmployees(){
        //logger.debug("Employee controller: /employee/all :");
        List<Wine> wines = this.wineService.listAllWines();
        return new ResponseEntity<>(wines, HttpStatus.OK);
    }


}
