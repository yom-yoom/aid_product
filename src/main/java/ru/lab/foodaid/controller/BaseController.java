package ru.lab.foodaid.controller;

import ru.lab.foodaid.model.UserProfile;

public abstract class BaseController {

    protected boolean hasAccess(UserProfile currentProfile, UserProfile requiredProfile) {
        return currentProfile == requiredProfile;
    }
}
