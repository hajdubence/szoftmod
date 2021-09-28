package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import hu.unideb.notetakingapp.api.entity.User;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    private final LoggedInUserBean loggedInUserBean;

    private final UserService userService;

    private final NoteService noteService;

    public LoginController(LoggedInUserBean loggedInUserBean, UserService userService, NoteService noteService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping({"", "/login"})
    public String LoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping({"", "/login"})
    public String LoginSubmit(@ModelAttribute User user, Model model) {
        loggedInUserBean.setLoggedInUser(user);
        model.addAttribute("user", user);
        model.addAttribute("users",userService.findAll());
        model.addAttribute("notes",noteService.findAll());
        //TODO: authenticaton
        return "redirect:/notes";
    }

}