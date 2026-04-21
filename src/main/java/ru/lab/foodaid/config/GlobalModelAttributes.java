package ru.lab.foodaid.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.lab.foodaid.model.UserProfile;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentProfile")
    public UserProfile currentProfile(HttpSession session) {
        Object current = session.getAttribute("currentProfile");
        if (current instanceof UserProfile profile) {
            return profile;
        }
        UserProfile profile = UserProfile.CLIENT;
        session.setAttribute("currentProfile", profile);
        return profile;
    }
}
