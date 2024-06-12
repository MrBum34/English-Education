package by.vstu.english_education.controller;

import by.vstu.english_education.entity.User;
import by.vstu.english_education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MainController {
@Autowired
private UserService userService;
    @GetMapping()
    public String main() {
        return "main";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userService.findByUsername(user.getUsername())!=null) {
            model.addAttribute("error", "Пользователь с таким именем пользователя уже существует");
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }
}
