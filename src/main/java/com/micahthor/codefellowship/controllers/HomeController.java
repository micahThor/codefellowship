package com.micahthor.codefellowship.controllers;

import com.micahthor.codefellowship.models.ApplicationUser;
import com.micahthor.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository userRepository;

    @GetMapping("/")
    public String getIndex(Principal p, Model m) {

        // get registered profiles from database to dispay on index
        List<ApplicationUser> registeredUsers = userRepository.findAll();

        if (p != null) {
            m.addAttribute("userName", p.getName());
            // don't display current logged in user's profile
            registeredUsers.remove(userRepository.findByUserName(p.getName()));
        }

        m.addAttribute("registeredUsers", registeredUsers);

        return "index";
    }
}
