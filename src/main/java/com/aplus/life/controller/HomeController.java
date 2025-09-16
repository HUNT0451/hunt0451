package com.aplus.life.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller 
public class HomeController {

    @GetMapping(value="/")    
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Life Application Home Page!");
        return "index";
    }

}
