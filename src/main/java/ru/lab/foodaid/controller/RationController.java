package ru.lab.foodaid.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.lab.foodaid.model.Ration;
import ru.lab.foodaid.model.UserProfile;
import ru.lab.foodaid.service.RationService;

@Controller
public class RationController extends BaseController {

    private final RationService rationService;

    public RationController(RationService rationService) {
        this.rationService = rationService;
    }

    @GetMapping("/rations")
    public String listRations(Model model) {
        model.addAttribute("pageTitle", "Каталог рационов");
        model.addAttribute("rations", rationService.findActiveRations());
        return "rations";
    }

    @GetMapping("/admin/rations/new")
    public String newRation(@ModelAttribute("currentProfile") UserProfile currentProfile,
                            Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        model.addAttribute("pageTitle", "Добавление рациона");
        model.addAttribute("ration", new Ration());
        return "ration-form";
    }

    @PostMapping("/admin/rations")
    public String createRation(@ModelAttribute("currentProfile") UserProfile currentProfile,
                               @Valid @ModelAttribute("ration") Ration ration,
                               BindingResult bindingResult,
                               Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Добавление рациона");
            return "ration-form";
        }
        rationService.save(ration);
        return "redirect:/rations";
    }
}
