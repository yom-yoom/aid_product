package ru.lab.foodaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiary")
    private Long id;

    @NotBlank
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank
    @Column(nullable = false)
    private String phone;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String address;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @Column(name = "benefit_status")
    private String benefitStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Beneficiary() {
    }

    public Beneficiary(String fullName, String phone, String address, String category) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.category = category;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (benefitStatus == null || benefitStatus.isBlank()) {
            benefitStatus = "Новая запись";
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getBenefitStatus() { return benefitStatus; }
    public void setBenefitStatus(String benefitStatus) { this.benefitStatus = benefitStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // alias methods for backwards compatibility with current code / docs
    public LocalDateTime getEventDate() { return createdAt; }
    public void setEventDate(LocalDateTime eventDate) { this.createdAt = eventDate; }
}
