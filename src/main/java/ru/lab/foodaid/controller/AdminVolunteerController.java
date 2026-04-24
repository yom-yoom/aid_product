package ru.lab.foodaid.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lab.foodaid.model.UserProfile;
import ru.lab.foodaid.model.Volunteer;
import ru.lab.foodaid.service.VolunteerService;

@Controller
@RequestMapping("/admin/volunteers")
public class AdminVolunteerController extends BaseController {

    private final VolunteerService volunteerService;

    public AdminVolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public String listVolunteers(@ModelAttribute("currentProfile") UserProfile currentProfile, Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Управление волонтёрами");
        model.addAttribute("volunteers", volunteerService.findAll());
        return "volunteers";
    }

    @GetMapping("/new")
    public String newVolunteerForm(@ModelAttribute("currentProfile") UserProfile currentProfile, Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Добавление волонтёра");
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("transportTypes", new String[]{"Личный автомобиль", "Велосипед", "Пешком", "Мотоцикл", "Грузовик"});
        model.addAttribute("availabilityStatuses", new String[]{"Доступен", "Занят", "Недоступен"});
        return "volunteer-form";
    }

    @PostMapping
    public String createVolunteer(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                  @Valid @ModelAttribute("volunteer") Volunteer volunteer,
                                  BindingResult result,
                                  Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Добавление волонтёра");
            model.addAttribute("transportTypes", new String[]{"Личный автомобиль", "Велосипед", "Пешком", "Мотоцикл", "Грузовик"});
            model.addAttribute("availabilityStatuses", new String[]{"Доступен", "Занят", "Недоступен"});
            return "volunteer-form";
        }
        volunteerService.save(volunteer);
        return "redirect:/admin/volunteers";
    }

    @GetMapping("/{id}/edit")
    public String editVolunteerForm(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                    @PathVariable Long id,
                                    Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Редактирование волонтёра");
        model.addAttribute("volunteer", volunteerService.getById(id));
        model.addAttribute("transportTypes", new String[]{"Личный автомобиль", "Велосипед", "Пешком", "Мотоцикл", "Грузовик"});
        model.addAttribute("availabilityStatuses", new String[]{"Доступен", "Занят", "Недоступен"});
        return "volunteer-form";
    }

    @PostMapping("/{id}")
    public String updateVolunteer(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                  @PathVariable Long id,
                                  @Valid @ModelAttribute("volunteer") Volunteer volunteer,
                                  BindingResult result,
                                  Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Редактирование волонтёра");
            model.addAttribute("transportTypes", new String[]{"Личный автомобиль", "Велосипед", "Пешком", "Мотоцикл", "Грузовик"});
            model.addAttribute("availabilityStatuses", new String[]{"Доступен", "Занят", "Недоступен"});
            return "volunteer-form";
        }
        volunteer.setId(id);
        volunteerService.save(volunteer);
        return "redirect:/admin/volunteers";
    }
}
