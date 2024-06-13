package by.vstu.english_education.controller;

import by.vstu.english_education.entity.Dto.UserProfileDTO;
import by.vstu.english_education.entity.User;
import by.vstu.english_education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setFirstname(user.getFirstname());
        userProfileDTO.setLastname(user.getLastname());
        userProfileDTO.setPatronymic(user.getPatronymic());
        model.addAttribute("userProfile", userProfileDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(UserProfileDTO userProfileDTO, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        userService.updateUserProfile(username, userProfileDTO);
        return "redirect:/profile";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        userService.changePassword(username, newPassword);
        return "redirect:/profile";

    }
}
