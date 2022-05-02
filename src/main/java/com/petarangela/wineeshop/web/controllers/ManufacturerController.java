package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.model.Manufacturer;
import com.petarangela.wineeshop.service.impl.ManufacturerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerServiceImpl manufacturerService;

    public ManufacturerController(ManufacturerServiceImpl manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getCategoriesPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        this.manufacturerService.deleteById(id);
        return "redirect:/manufacturers";
    }

    @GetMapping("/edit-form/{id}")
    public String editManufacturerPage(@PathVariable Long id, Model model) {
        if (this.manufacturerService.findById(id).isPresent()) {
            Manufacturer manufacturer = this.manufacturerService.findById(id).get();
            model.addAttribute("manufacturer", manufacturer);
            model.addAttribute("bodyContent", "add-manufacturer");
            return "add-manufacturer";
        }
        return "redirect:/wines?error=ManufacturerNotFound";
    }

    @GetMapping("/add-form")
    // TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addManufacturerPage(Model model) {
        model.addAttribute("bodyContent", "add-manufacturer");
        return "add-manufacturer";
    }

    @PostMapping("/add")
    public String saveCategory(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String address) {
        if (id != null) {
            this.manufacturerService.update(id, name, address);
        } else {
            this.manufacturerService.create(name, address);
        }
        return "redirect:/manufacturers";
    }
}
