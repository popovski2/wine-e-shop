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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("sessionId", session.getId());

        return "index";
    }

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<String> msgs = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (msgs == null) {
            msgs = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
        }
        msgs.add(msg);
        request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
        return "redirect:/";
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

            //put it to redis
            request.getSession().setAttribute("username",session.getAttribute("username"));

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
