package ru.lab.foodaid.service;

import org.springframework.stereotype.Service;
import ru.lab.foodaid.model.Product;
import ru.lab.foodaid.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findActiveProducts() {
        return productRepository.findByActiveTrueOrderByNameAsc();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден: " + id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public long countActive() {
        return productRepository.findByActiveTrueOrderByNameAsc().size();
    }

    public List<Product> findAll() {
        return productRepository.findAllByOrderByNameAsc();
    }
}
