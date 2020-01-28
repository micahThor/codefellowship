package com.micahthor.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex(Principal p, Model m) {

        if (p != null) {
            m.addAttribute("userName", p.getName());
        }

        return "index";
    }
}
