package ru.lab.foodaid.model;

public enum RequestStatus {
    NEW("Новая"),
    APPROVED("Одобрена"),
    ASSIGNED("Назначена на доставку"),
    DONE("Доставлена"),
    REJECTED("Отклонена");

    private final String label;

    RequestStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
