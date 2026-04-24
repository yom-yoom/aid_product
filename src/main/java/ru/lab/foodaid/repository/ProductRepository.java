package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrueOrderByNameAsc();
    List<Product> findAllByOrderByNameAsc();
}
