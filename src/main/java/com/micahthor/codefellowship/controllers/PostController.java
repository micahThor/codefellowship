package com.micahthor.codefellowship.controllers;

import com.micahthor.codefellowship.models.ApplicationUser;
import com.micahthor.codefellowship.models.ApplicationUserRepository;
import com.micahthor.codefellowship.models.Post;
import com.micahthor.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/writePost")
    public RedirectView writePost(Principal p, String body) {

        // https://mkyong.com/java8/java-8-how-to-format-localdatetime/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String now = LocalDateTime.now().format(formatter);

        if (p != null) {
            ApplicationUser user = userRepository.findByUserName(p.getName());
            Post newPost = new Post(user, body, now);
            postRepository.save(newPost);
        }
        return new RedirectView("/userProfile");
    }
}
