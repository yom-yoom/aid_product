package ru.lab.foodaid.model;

public enum DeliveryStatus {
    PLANNED("Запланирована"),
    IN_PROGRESS("В доставке"),
    DONE("Доставлена");

    private final String label;

    DeliveryStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
