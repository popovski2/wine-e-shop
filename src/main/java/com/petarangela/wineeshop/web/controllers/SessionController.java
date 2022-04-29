package com.petarangela.wineeshop.web.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.petarangela.wineeshop.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proba")
public class SessionController {

    @GetMapping()
    public String getProba(){ return "home";}

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        Integer pageViews = 1;
        if (request.getSession().getAttribute("pageViews") != null) {
            pageViews += (Integer) request.getSession().getAttribute("pageViews");
        }
        request.getSession().setAttribute("pageViews", pageViews);
        model.addAttribute("pageViews", pageViews);
        return "home";
    }

    @PostMapping("/addNote")
    public String addNote(@RequestParam("note") String note, HttpServletRequest request) {
        //get the notes from request session
        List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
        //check if notes is present in session or not
        if (notes == null) {
            notes = new ArrayList<>();
            // if notes object is not present in session, set notes in the request session
            request.getSession().setAttribute("NOTES_SESSION", notes);
        }
        notes.add(note);
        request.getSession().setAttribute("NOTES_SESSION", notes);
        return "redirect:/login";
    }
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        List<String> notes = (List<String>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("notesSession", notes!=null? notes:new ArrayList<>());
        return "home";
    }
    @PostMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return "redirect:/home";
    }
}