package ru.lab.foodaid.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lab.foodaid.model.RequestStatus;
import ru.lab.foodaid.model.UserProfile;
import ru.lab.foodaid.service.DeliveryService;
import ru.lab.foodaid.service.RequestService;
import ru.lab.foodaid.service.VolunteerService;

import java.time.LocalDateTime;

@Controller
public class AdminController extends BaseController {

    private final RequestService requestService;
    private final VolunteerService volunteerService;
    private final DeliveryService deliveryService;

    public AdminController(RequestService requestService,
                           VolunteerService volunteerService,
                           DeliveryService deliveryService) {
        this.requestService = requestService;
        this.volunteerService = volunteerService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/admin/requests")
    public String adminRequests(@ModelAttribute("currentProfile") UserProfile currentProfile, Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Заявки администратора");
        model.addAttribute("requests", requestService.findAll());
        model.addAttribute("statuses", RequestStatus.values());
        model.addAttribute("volunteers", volunteerService.findActive());
        return "admin-requests";
    }

    @PostMapping("/admin/requests/{id}/status")
    public String updateStatus(@ModelAttribute("currentProfile") UserProfile currentProfile,
                               @PathVariable Long id,
                               @RequestParam RequestStatus status,
                               @RequestParam(required = false) String adminComment) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        requestService.updateStatus(id, status, adminComment);
        return "redirect:/admin/requests";
    }

    @PostMapping("/admin/requests/{id}/assign-delivery")
    public String assignDelivery(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                 @PathVariable Long id,
                                 @RequestParam Long volunteerId,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime scheduledAt,
                                 @RequestParam(required = false) String deliveryComment) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        deliveryService.assignDelivery(id, volunteerId, scheduledAt, deliveryComment);
        return "redirect:/admin/requests";
    }
}
