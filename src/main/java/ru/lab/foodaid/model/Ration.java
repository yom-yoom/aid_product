package ru.lab.foodaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ration")
public class Ration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ration")
    private Long id;

    @NotBlank(message = "Введите название рациона")
    @Column(name = "title", nullable = false)
    private String name;

    @Column(name = "period")
    private String period;

    @NotBlank(message = "Введите описание рациона")
    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "ration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RationComposition> compositions = new ArrayList<>();

    public Ration() {}

    public Ration(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public List<RationComposition> getCompositions() { return compositions; }
    public void setCompositions(List<RationComposition> compositions) { this.compositions = compositions; }
}
