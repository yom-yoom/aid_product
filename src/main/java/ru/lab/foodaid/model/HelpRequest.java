package ru.lab.foodaid.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "help_request")
public class HelpRequest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request")
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_beneficiary", nullable = false)
    private Beneficiary beneficiary;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ration", nullable = false)
    private Ration ration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status = RequestStatus.NEW;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "comment", length = 1000)
    private String adminComment;

    @Column(name = "delivery_address", length = 1000)
    private String deliveryAddress;

    @Column(name = "intake_channel")
    private String receiptChannel;

    @OneToOne(mappedBy = "helpRequest", cascade = CascadeType.ALL)
    private Delivery delivery;

    public HelpRequest() {}

    public HelpRequest(Beneficiary beneficiary, Ration ration, RequestStatus status, String adminComment) {
        this.beneficiary = beneficiary;
        this.ration = ration;
        this.status = status;
        this.adminComment = adminComment;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = RequestStatus.NEW;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Beneficiary getBeneficiary() { return beneficiary; }
    public void setBeneficiary(Beneficiary beneficiary) { this.beneficiary = beneficiary; }
    public Ration getRation() { return ration; }
    public void setRation(Ration ration) { this.ration = ration; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getAdminComment() { return adminComment; }
    public void setAdminComment(String adminComment) { this.adminComment = adminComment; }
    public Delivery getDelivery() { return delivery; }
    public void setDelivery(Delivery delivery) { this.delivery = delivery; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public String getReceiptChannel() { return receiptChannel; }
    public void setReceiptChannel(String receiptChannel) { this.receiptChannel = receiptChannel; }
    public String getCreatedAtLabel() { return createdAt == null ? "" : createdAt.format(FORMATTER); }
}
