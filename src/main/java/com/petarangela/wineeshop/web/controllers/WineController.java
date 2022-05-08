package com.petarangela.wineeshop.web.controllers;

import com.google.gson.Gson;
import com.petarangela.wineeshop.model.*;
import com.petarangela.wineeshop.service.CategoryService;
import com.petarangela.wineeshop.service.ManufacturerService;
import com.petarangela.wineeshop.service.TypeService;
import com.petarangela.wineeshop.service.WineService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wines")
public class WineController {

    private final WineService wineService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;


    public WineController(WineService wineService,
                          CategoryService categoryService,
                          ManufacturerService manufacturerService, TypeService typeService) {
        this.wineService = wineService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
        this.typeService = typeService;
    }


    @GetMapping
    public String getWinesPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Wine> wines = this.wineService.listAllWines();
        model.addAttribute("wines",wines);
        return "wines";
    }

    /*@GetMapping
    public String getWinePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Wine> wines = this.wineService.listAllWines();
        model.addAttribute("wines", wines);
        model.addAttribute("bodyContent", "wines");
        return "master-template";
    }*/

    @DeleteMapping("/delete/{id}")
    public String deleteWine(@PathVariable Long id) {
        this.wineService.deleteById(id);
        return "redirect:/wines";
    }

    @GetMapping("/edit-form/{id}")
    public String editWinePage(@PathVariable Long id, Model model) {
        if (this.wineService.findById(id).isPresent()) {
            Wine wine = this.wineService.findById(id).get();
            List<Category> categories = this.categoryService.listAll();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Type> types = this.typeService.listAllTypes();
            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("types", types);
            model.addAttribute("wine", wine);
            model.addAttribute("bodyContent", "add-wine");
            return "add-wine";
        }
        return "redirect:/wines?error=WineNotFound";
    }

    @GetMapping("/add-form")
    // TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addWinePage(Model model) {
        List<Category> categories = this.categoryService.listAll();
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Type> types = this.typeService.listAllTypes();
        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("types", types);
        model.addAttribute("bodyContent", "add-wine");
        return "add-wine";
    }

    @PostMapping("/add")
    public String saveWine(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam String imageUrl,
            @RequestParam Long categoryId,
            @RequestParam Long manufacturerId,
            @RequestParam Long typeId) {
        if (id != null) {
            this.wineService.update(id, name, price, quantity, imageUrl, categoryId, manufacturerId, typeId);
        } else {
            this.wineService.create(name, price, quantity,  imageUrl, categoryId, manufacturerId, typeId);
        }
        return "redirect:/wines";
    }

    @GetMapping(value = "/types")
    //@ResponseBody
    public List<Type> getTypes(@RequestParam Long id) {
        return this.typeService.listAllTypes().stream()
                .filter(t -> t.getCategory().getId().equals(id))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @RequestMapping(value = "loadTypesByCategory/{id}", method = RequestMethod.GET)
    public String loadTypesByCategory(@PathVariable("id") Long id) {
        Gson gson = new Gson();
        return gson.toJson(typeService.findAllByCategoryId(id));
    }


    @GetMapping("/getWinesBy/{name}")
    public String getWinesBy(@PathVariable String name, Model model) {
        if (this.categoryService.findByName(name) != null) {
            List<Wine> winesByCategory = this.wineService.listWinesByCategory(name);
            model.addAttribute("winesByCategory", winesByCategory);
            return "redirect:/wines";
        }
        return "redirect:/wines?error=WineNotFound";
    }

}