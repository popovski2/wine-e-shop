package com.petarangela.wineeshop.web.controllers;

import com.petarangela.wineeshop.model.User;
import com.petarangela.wineeshop.model.exceptions.InvalidUserCredentialsException;
import com.petarangela.wineeshop.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private HttpSession session=null;

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.userService.login(request.getParameter("username"),
                    request.getParameter("password"));
            session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", request.getParameter("username"));
            System.out.println("The current username is:" + session.getAttribute("username"));

            return "redirect:/api/wines/all";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    public String getUsernameFromSession() {
        return (String) session.getAttribute("username");
    }
}
