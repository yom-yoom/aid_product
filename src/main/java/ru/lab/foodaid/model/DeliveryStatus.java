package ru.lab.foodaid.model;

public enum DeliveryStatus {
    ASSIGNED("Назначена"),
    DONE("Доставлена"),
    CANCELED("Отменена");

    private final String label;

    DeliveryStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
