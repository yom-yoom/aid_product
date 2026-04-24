package ru.lab.foodaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_volunteer")
    private Long id;

    @NotBlank
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "transport_type")
    private String transportType;

    @Column(name = "availability_status", nullable = false)
    private String availabilityStatus = "Доступен";

    public Volunteer() {}

    public Volunteer(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getTransportType() { return transportType; }
    public void setTransportType(String transportType) { this.transportType = transportType; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    @Transient
    public boolean isActive() {
        return availabilityStatus != null && !availabilityStatus.equalsIgnoreCase("Недоступен");
    }

    public void setActive(boolean active) {
        if (!active) {
            this.availabilityStatus = "Недоступен";
        } else if (this.availabilityStatus == null || this.availabilityStatus.isBlank() || this.availabilityStatus.equalsIgnoreCase("Недоступен")) {
            this.availabilityStatus = "Доступен";
        }
    }
}
