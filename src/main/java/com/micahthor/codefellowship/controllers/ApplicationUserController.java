package com.micahthor.codefellowship.controllers;

import com.micahthor.codefellowship.models.ApplicationUser;
import com.micahthor.codefellowship.models.ApplicationUserRepository;
import com.micahthor.codefellowship.models.Post;
import com.micahthor.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    PostRepository postRepository;

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
    public RedirectView createUser(HttpServletRequest request, String userName, String password, String firstName, String lastName, String dateOfBirth, String bio, URL profilePicture) {;

        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio, profilePicture);

        userRepository.save(newUser);

        try {
            request.login(userName, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return new RedirectView("/");
    }

    @GetMapping("/userProfile")
    public String showUserProfileDetails(Model m, Principal p) {

        // if user accesses route with authenticated login
        if (p != null) {
            // make user details available to page
            ApplicationUser loggedInUser = userRepository.findByUserName(p.getName());
            m.addAttribute("user", loggedInUser);
            m.addAttribute("userName", loggedInUser.getUserName());

            // make post list available to page
            List<Post> postList = postRepository.findByApplicationUserId(loggedInUser.getId());
            m.addAttribute("posts", postList);

            return "userProfile";
        }

        // if user accesses route without login
        return "login";
    }

    @GetMapping("/visitFriend")
    public String displayRegisteredUserProfile(String userName, Model m, Principal p) {

        if (p != null) {
            // make user details available to page
            ApplicationUser loggedInUser = userRepository.findByUserName(p.getName());
            m.addAttribute("loggedInUser", loggedInUser);
            m.addAttribute("userName", loggedInUser.getUserName());

            ApplicationUser userToVisit = userRepository.findByUserName(userName);
            m.addAttribute("user", userToVisit);
            return "friendPage";
        }

        // if user accesses route without login
        return "login";
    }

    @PostMapping("/followFriend")
    public RedirectView followFriend(String userNameToFollow, Principal p) {

        if (p != null) {
            ApplicationUser userToFollow = userRepository.findByUserName(userNameToFollow);
            ApplicationUser loggedInUser = userRepository.findByUserName(p.getName());
            loggedInUser.followAFriend(userToFollow);

            userRepository.save(loggedInUser);
        }
        return new RedirectView("/");
    }

    @GetMapping("/feed")
    public String viewFeed(Principal p, Model m) {

        if (p != null) {
            ApplicationUser registeredUser = userRepository.findByUserName(p.getName());
            m.addAttribute("userName", registeredUser.getUserName());
            Set<ApplicationUser> friends = registeredUser.getFriendsThatIAmFollowing();
            m.addAttribute("friends", friends);

            return "feed";
        }

        // if user accesses route without login
        return "login";
    }
}
