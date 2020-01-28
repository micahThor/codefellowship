package com.micahthor.codefellowship.controllers;

import com.micahthor.codefellowship.models.ApplicationUser;
import com.micahthor.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URL;
import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository userRepository;

    @GetMapping("/login")
    public String index(Model m, Principal p) {
        if (p != null) {
            m.addAttribute("userName", p.getName());
        }

        return "login";
    }

    @GetMapping("/signup")
    public String showSignUpPage(Model m, Principal p) {

        if (p != null) {
            m.addAttribute("userName", p.getName());
        }

        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String userName, String password, String firstName, String lastName, String dateOfBirth, String bio, URL profilePicture) {;

        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio, profilePicture);

        userRepository.save(newUser);

        return new RedirectView("/");
    }
}
