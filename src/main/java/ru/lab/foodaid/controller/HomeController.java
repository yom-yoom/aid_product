package ru.lab.foodaid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lab.foodaid.service.DeliveryService;
import ru.lab.foodaid.service.ProductService;
import ru.lab.foodaid.service.RationService;
import ru.lab.foodaid.service.RequestService;
import ru.lab.foodaid.service.VolunteerService;

@Controller
public class HomeController extends BaseController {

    private final RationService rationService;
    private final RequestService requestService;
    private final DeliveryService deliveryService;
    private final ProductService productService;
    private final VolunteerService volunteerService;

    public HomeController(RationService rationService,
                          RequestService requestService,
                          DeliveryService deliveryService,
                          ProductService productService,
                          VolunteerService volunteerService) {
        this.rationService = rationService;
        this.requestService = requestService;
        this.deliveryService = deliveryService;
        this.productService = productService;
        this.volunteerService = volunteerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Главная страница");
        model.addAttribute("rationCount", rationService.countActive());
        model.addAttribute("requestCount", requestService.countAll());
        model.addAttribute("deliveryCount", deliveryService.countAll());
        model.addAttribute("productCount", productService.countActive());
        model.addAttribute("volunteerCount", volunteerService.findAll().size());
        return "index";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("pageTitle", "Доступ ограничен");
        return "access-denied";
    }
}
