package ru.lab.foodaid.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lab.foodaid.model.UserProfile;

@Controller
public class ProfileController {

    @GetMapping("/profile/select")
    public String selectProfile(@RequestParam("role") UserProfile role, HttpSession session) {
        session.setAttribute("currentProfile", role);
        return "redirect:/";
    }
}
