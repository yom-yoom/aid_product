package ru.lab.foodaid.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lab.foodaid.dto.RequestCreateForm;
import ru.lab.foodaid.service.RationService;
import ru.lab.foodaid.service.RequestService;

@Controller
public class RequestController extends BaseController {

    private final RequestService requestService;
    private final RationService rationService;

    public RequestController(RequestService requestService, RationService rationService) {
        this.requestService = requestService;
        this.rationService = rationService;
    }

    @GetMapping("/requests/new")
    public String newRequest(@RequestParam(value = "rationId", required = false) Long rationId,
                             Model model) {
        RequestCreateForm form = new RequestCreateForm();
        form.setRationId(rationId);
        model.addAttribute("pageTitle", "Оформление заявки");
        model.addAttribute("requestForm", form);
        model.addAttribute("rations", rationService.findActiveRations());
        return "request-form";
    }

    @PostMapping("/requests")
    public String createRequest(@Valid @ModelAttribute("requestForm") RequestCreateForm requestForm,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Оформление заявки");
            model.addAttribute("rations", rationService.findActiveRations());
            return "request-form";
        }
        requestService.createRequest(requestForm);
        return "redirect:/requests/success";
    }

    @GetMapping("/requests/success")
    public String requestSuccess(Model model) {
        model.addAttribute("pageTitle", "Заявка отправлена");
        return "request-success";
    }
}
