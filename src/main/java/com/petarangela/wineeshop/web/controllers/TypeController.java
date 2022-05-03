package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.service.CategoryService;
import com.petarangela.wineeshop.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;
    private final CategoryService categoryService;

    public TypeController(TypeService typeService, CategoryService categoryService) {
        this.typeService = typeService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getTypesPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Type> types = this.typeService.listAllTypes();
        model.addAttribute("types", types);
        return "types";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteType(@PathVariable Long id) {
        this.typeService.deleteById(id);
        return "redirect:/types";
    }

    @GetMapping("/edit-form/{id}")
    public String editTypePage(@PathVariable Long id, Model model) {
        if (this.typeService.findById(id).isPresent()) {
            Type type = this.typeService.findById(id).get();
            List<Category> categories = this.categoryService.listAll();
            model.addAttribute("type", type);
            model.addAttribute("categories", categories);
            model.addAttribute("bodyContent", "add-type");
            return "add-type";
        }
        return "redirect:/wines?error=TypeNotFound";
    }

    @GetMapping("/add-form")
    // TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTypePage(Model model) {
        List<Category> categories = this.categoryService.listAll();
        model.addAttribute("bodyContent", "add-type");
        model.addAttribute("categories", categories);
        return "add-type";
    }

    @PostMapping("/add")
    public String saveCategory(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long categoryId) {
        if (id != null) {
            this.typeService.update(id, name, description, categoryId);
        } else {
            this.typeService.create(name, description, categoryId);
        }
        return "redirect:/types";
    }

}
