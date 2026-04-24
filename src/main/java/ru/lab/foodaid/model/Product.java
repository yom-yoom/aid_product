package ru.lab.foodaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @NotBlank(message = "Введите название продукта")
    @Column(name = "title", nullable = false)
    private String name;

    @NotBlank(message = "Введите единицу измерения")
    @Column(name = "unit", nullable = false)
    private String unitOfMeasurement;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Product() {}

    public Product(String name, String unitOfMeasurement, boolean active) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUnitOfMeasurement() { return unitOfMeasurement; }
    public void setUnitOfMeasurement(String unitOfMeasurement) { this.unitOfMeasurement = unitOfMeasurement; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
