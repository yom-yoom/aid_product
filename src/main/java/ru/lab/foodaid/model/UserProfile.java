package ru.lab.foodaid.model;

public enum UserProfile {
    CLIENT("Получатель помощи"),
    ADMIN("Администратор"),
    VOLUNTEER("Волонтёр");

    private final String label;

    UserProfile(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
