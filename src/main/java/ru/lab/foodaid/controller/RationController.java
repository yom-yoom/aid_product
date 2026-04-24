package ru.lab.foodaid.controller;

import jakarta.validation.Valid;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lab.foodaid.model.UserProfile;
import ru.lab.foodaid.model.Ration;
import ru.lab.foodaid.service.ProductService;
import ru.lab.foodaid.service.RationService;

@Controller
public class RationController extends BaseController {

    private final RationService rationService;
    private final ProductService productService;

    public RationController(RationService rationService, ProductService productService) {
        this.rationService = rationService;
        this.productService = productService;
    }

    @GetMapping("/rations")
    public String listRations(@ModelAttribute("currentProfile") UserProfile currentProfile,
                              Model model) {
        model.addAttribute("pageTitle", "Каталог рационов");
        if (currentProfile == UserProfile.ADMIN) {
            model.addAttribute("rations", rationService.findAll());
        } else {
            model.addAttribute("rations", rationService.findActiveRations());
        }
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

    @PostMapping("/admin/rations/{id}/deactivate")
    public String deactivateRation(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                   @PathVariable Long id) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        rationService.deactivate(id);
        return "redirect:/rations";
    }

    @PostMapping("/admin/rations/{id}/activate")
    public String activateRation(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                 @PathVariable Long id) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        rationService.activate(id);
        return "redirect:/rations";
    }

    @GetMapping("/admin/rations/{id}/composition")
    public String composition(@ModelAttribute("currentProfile") UserProfile currentProfile,
                              @PathVariable Long id,
                              Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        model.addAttribute("pageTitle", "Состав рациона");
        model.addAttribute("ration", rationService.getById(id));
        model.addAttribute("compositions", rationService.getCompositions(id));
        model.addAttribute("products", productService.findActiveProducts());
        return "ration-composition";
    }

    @PostMapping("/admin/rations/{id}/composition")
    public String addComposition(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                 @PathVariable Long id,
                                 @RequestParam Long productId,
                                 @RequestParam BigDecimal quantity) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        rationService.addProductToRation(id, productId, quantity);
        return "redirect:/admin/rations/" + id + "/composition";
    }

    @PostMapping("/admin/rations/{rationId}/composition/{productId}/delete")
    public String deleteComposition(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                    @PathVariable Long rationId,
                                    @PathVariable Long productId) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) {
            return "redirect:/access-denied";
        }
        rationService.removeProductFromRation(rationId, productId);
        return "redirect:/admin/rations/" + rationId + "/composition";
    }
}
