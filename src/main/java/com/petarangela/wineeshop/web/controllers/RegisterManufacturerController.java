package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.model.exceptions.InvalidArgumentsException;
import com.petarangela.wineeshop.model.exceptions.PasswordsDoNotMatchException;
import com.petarangela.wineeshop.service.ManufacturerService;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registerManufacturer")
public class RegisterManufacturerController {

    private final UserService userService;
    private final ManufacturerService manufacturerService;

    public RegisterManufacturerController(UserService userService, ManufacturerService manufacturerService) {
        this.userService = userService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getRegisterManufacturerPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "registerManufacturer";
    }

    @PostMapping
    public String registerManufacturer(@RequestParam String username,
                                       @RequestParam String password,
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam String address
    ) {
        try{
            this.userService.register(username, password, name, surname);
            this.manufacturerService.create(name, address);
            return "redirect:http://localhost:9091/";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
