package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.filter.CustomUsernamePasswordAuthenticationProvider;
import com.petarangela.wineeshop.model.exceptions.InvalidArgumentsException;
import com.petarangela.wineeshop.model.exceptions.PasswordsDoNotMatchException;
import com.petarangela.wineeshop.service.ManufacturerService;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registerManufacturer")
public class RegisterManufacturerController {

    private final UserService userService;
    private final ManufacturerService manufacturerService;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;


    public RegisterManufacturerController(UserService userService, ManufacturerService manufacturerService, CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.manufacturerService = manufacturerService;
        this.authenticationProvider = authenticationProvider;
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
    public String registerManufacturer(HttpServletRequest request,
                                       @RequestParam String username,
                                       @RequestParam String password,
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam String address
    ) {
        try{
            this.userService.register(username, password, name, surname);
            this.manufacturerService.create(name, address);
            //auto login after registration
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            authToken.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = this.authenticationProvider.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:http://localhost:9091/home";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
