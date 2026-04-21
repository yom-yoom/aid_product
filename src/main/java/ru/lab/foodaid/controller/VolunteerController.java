package ru.lab.foodaid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lab.foodaid.model.UserProfile;
import ru.lab.foodaid.service.DeliveryService;

@Controller
public class VolunteerController extends BaseController {

    private final DeliveryService deliveryService;

    public VolunteerController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/volunteer/deliveries")
    public String deliveries(@ModelAttribute("currentProfile") UserProfile currentProfile,
                             Model model) {
        if (!hasAccess(currentProfile, UserProfile.VOLUNTEER)) {
            return "redirect:/access-denied";
        }
        model.addAttribute("pageTitle", "Доставки волонтёра");
        model.addAttribute("deliveries", deliveryService.findAll());
        return "volunteer-deliveries";
    }

    @PostMapping("/volunteer/deliveries/{id}/complete")
    public String complete(@ModelAttribute("currentProfile") UserProfile currentProfile,
                           @PathVariable Long id,
                           @RequestParam(required = false) String resultComment) {
        if (!hasAccess(currentProfile, UserProfile.VOLUNTEER)) {
            return "redirect:/access-denied";
        }
        deliveryService.completeDelivery(id, resultComment);
        return "redirect:/volunteer/deliveries";
    }
}
