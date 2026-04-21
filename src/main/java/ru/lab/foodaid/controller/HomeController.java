package ru.lab.foodaid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lab.foodaid.service.DeliveryService;
import ru.lab.foodaid.service.RationService;
import ru.lab.foodaid.service.RequestService;

@Controller
public class HomeController extends BaseController {

    private final RationService rationService;
    private final RequestService requestService;
    private final DeliveryService deliveryService;

    public HomeController(RationService rationService,
                          RequestService requestService,
                          DeliveryService deliveryService) {
        this.rationService = rationService;
        this.requestService = requestService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Главная страница");
        model.addAttribute("rationCount", rationService.countActive());
        model.addAttribute("requestCount", requestService.countAll());
        model.addAttribute("deliveryCount", deliveryService.countAll());
        return "index";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("pageTitle", "Доступ ограничен");
        return "access-denied";
    }
}
