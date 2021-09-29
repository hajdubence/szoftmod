package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoggedInUserBean loggedInUserBean;

    public LoginController(LoggedInUserBean loggedInUserBean, UserService userService, NoteService noteService) {
        this.loggedInUserBean = loggedInUserBean;
    }

    @GetMapping({"", "/login"})
    public String LoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping({"", "/login"})
    public String LoginSubmit(@ModelAttribute User user, Model model) {
        loggedInUserBean.setLoggedInUser(user);

        if (loggedInUserBean.isLoggedIn())
            return "redirect:/notes";
        else
            return "login";
    }

}