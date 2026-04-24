package ru.lab.foodaid.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "delivery")
public class Delivery {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delivery")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_request", nullable = false, unique = true)
    private HelpRequest helpRequest;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_volunteer", nullable = false)
    private Volunteer volunteer;

    @Column(name = "planned_date", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "actual_date")
    private LocalDateTime deliveredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DeliveryStatus status = DeliveryStatus.ASSIGNED;

    @Column(name = "note", length = 1000)
    private String resultComment;

    public Delivery() {}

    public Delivery(HelpRequest helpRequest, Volunteer volunteer, LocalDateTime scheduledAt, DeliveryStatus status, String resultComment) {
        this.helpRequest = helpRequest;
        this.volunteer = volunteer;
        this.scheduledAt = scheduledAt;
        this.status = status;
        this.resultComment = resultComment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public HelpRequest getHelpRequest() { return helpRequest; }
    public void setHelpRequest(HelpRequest helpRequest) { this.helpRequest = helpRequest; }
    public Volunteer getVolunteer() { return volunteer; }
    public void setVolunteer(Volunteer volunteer) { this.volunteer = volunteer; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
    public DeliveryStatus getStatus() { return status; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
    public String getResultComment() { return resultComment; }
    public void setResultComment(String resultComment) { this.resultComment = resultComment; }
    public String getScheduledAtLabel() { return scheduledAt == null ? "" : scheduledAt.format(FORMATTER); }
}
