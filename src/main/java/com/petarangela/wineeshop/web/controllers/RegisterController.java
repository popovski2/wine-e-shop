package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.model.exceptions.InvalidArgumentsException;
import com.petarangela.wineeshop.model.exceptions.PasswordsDoNotMatchException;
import com.petarangela.wineeshop.service.ManufacturerService;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String surname
                           ) {
        try{
            this.userService.register(username, password, name, surname);
            return "redirect:http://localhost:9091/";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
