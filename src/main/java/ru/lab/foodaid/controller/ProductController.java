package ru.lab.foodaid.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lab.foodaid.model.Product;
import ru.lab.foodaid.model.UserProfile;
import ru.lab.foodaid.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class ProductController extends BaseController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(@ModelAttribute("currentProfile") UserProfile currentProfile, Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Управление продуктами");
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(@ModelAttribute("currentProfile") UserProfile currentProfile, Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Добавление продукта");
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping
    public String createProduct(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Добавление продукта");
            return "product-form";
        }
        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String editProductForm(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                  @PathVariable Long id,
                                  Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        model.addAttribute("pageTitle", "Редактирование продукта");
        model.addAttribute("product", productService.getById(id));
        return "product-form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@ModelAttribute("currentProfile") UserProfile currentProfile,
                                @PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model) {
        if (!hasAccess(currentProfile, UserProfile.ADMIN)) return "redirect:/access-denied";
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Редактирование продукта");
            return "product-form";
        }
        product.setId(id);
        productService.save(product);
        return "redirect:/admin/products";
    }
}
