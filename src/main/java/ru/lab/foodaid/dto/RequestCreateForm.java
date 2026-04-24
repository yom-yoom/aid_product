package ru.lab.foodaid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestCreateForm {

    @NotBlank(message = "Введите ФИО")
    private String fullName;

    @NotBlank(message = "Введите телефон")
    private String phone;

    @NotBlank(message = "Введите адрес")
    private String address;

    @NotBlank(message = "Выберите категорию помощи")
    private String category;

    @NotNull(message = "Выберите рацион")
    private Long rationId;

    private String deliveryAddress;
    private String receiptChannel;
    private String adminComment;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Long getRationId() { return rationId; }
    public void setRationId(Long rationId) { this.rationId = rationId; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public String getReceiptChannel() { return receiptChannel; }
    public void setReceiptChannel(String receiptChannel) { this.receiptChannel = receiptChannel; }
    public String getAdminComment() { return adminComment; }
    public void setAdminComment(String adminComment) { this.adminComment = adminComment; }
}
