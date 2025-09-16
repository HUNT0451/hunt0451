package com.aplus.life.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
public class SamplePageController {
    @GetMapping("/admin")
    public String admin(Model model, Authentication auth) {
        model.addAttribute("role", "ADMIN");
        model.addAttribute("user", auth.getName());
        return "admin";
    }

    @GetMapping("/badmin")
    public String badmin(Model model, Authentication auth) {
        model.addAttribute("role", "BADMIN");
        model.addAttribute("user", auth.getName());
        return "badmin";
    }

    @GetMapping("/user")
    public String user(Model model, Authentication auth) {
        model.addAttribute("role", "USER");
        model.addAttribute("user", auth.getName());
        return "user";
    }
}
